/*
 * DiscordSRV - https://github.com/DiscordSRV/DiscordSRV
 *
 * Copyright (C) 2016 - 2024 Austin "Scarsz" Shapiro
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */

package github.scarsz.discordsrv.listeners;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.util.DiscordUtil;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DiscordButtonListener extends ListenerAdapter {

    @Override
    public void onButtonClick(@NotNull ButtonClickEvent event) {
        if (event.getButton().getId().equals("drop-reward-button")) {

            System.out.println("HE CLICKED!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

            JSONObject jsonData;

            try {
                String content = new String(Files.readAllBytes(Paths.get("gems.json")));
                jsonData = new JSONObject(content);
            } catch (IOException | org.json.JSONException e) {
                // If the file doesn't exist or is not valid JSON, create a new JSONObject
                System.out.println("didn't find the file !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                jsonData = new JSONObject();
            }

            if (jsonData.keySet().contains(event.getMember().getId())) {
                System.out.println("found the id !!!!!!!!!!!!!!!!!!!!!!!!!!!!!! + " + event.getMember().getId());
                // if it already contains the member id, I add 1 to its score
                jsonData.put(event.getMember().getId(), (Integer) jsonData.get(event.getMember().getId()) + 1);


            }
            else {
                // otherwise, if the member doesn't have a score at all, I initialize it at 1
                jsonData.put(event.getMember().getId(), 1);
            }

            // Write the updated JSONObject to the file
            try (FileWriter file = new FileWriter("gems.json")) {
                file.write(jsonData.toString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }


            event.editMessage(DiscordSRV.getPlugin().getMessageWithTheButton().getContentRaw()).setActionRows(ActionRow.of(event.getButton().asDisabled())).queue();

            TextChannel textChannel = DiscordUtil.getJda().getTextChannelById(DiscordSRV.config().getString("DiscordDropMessageChannelId"));
            String reply = DiscordSRV.config().getString("DiscordGemClaimMessage");
            reply = reply.replaceAll("name", event.getMember().getEffectiveName());
            reply = reply.replaceAll("amount", "" + jsonData.get(event.getMember().getId()));
            textChannel.sendMessage(reply).queue();

//            event.getGuild().addRoleToMember(event.getMember(), event.getJDA().getRoleById(DiscordSRV.config().getString("DiscordDropMessageWinnerRoleId"))).queue();

            DiscordSRV.getPlugin().initDropMessage();

        }
        // it's the troll button from the "/gems all" command
        else if (event.getButton().getId().equals("purge-all-troll")) {

            event.getMember().getUser().openPrivateChannel().queue((channel) -> {
                channel.sendMessage(DiscordSRV.config().getString("DiscordPurgeButtonMessage")).queue();
            });
            event.deferEdit();
        }
    }
}
