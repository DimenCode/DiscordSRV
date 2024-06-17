package github.scarsz.discordsrv.listeners;

import github.scarsz.discordsrv.DiscordSRV;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import org.jetbrains.annotations.NotNull;

public class DiscordButtonListener extends ListenerAdapter {

    @Override
    public void onButtonClick(@NotNull ButtonClickEvent event) {
        if (event.getButton().getId().equals("drop-reward-button")) {
            event.getGuild().addRoleToMember(event.getUser().getId(), event.getJDA().getRoleById(DiscordSRV.config().getString("DiscordDropMessageWinnerRoleId")));

            event.getMessage().editMessage(event.getMessage().getContentRaw()).setActionRows(ActionRow.of(event.getButton().asDisabled())).queue();

            event.getMessage().getTextChannel().sendMessage(event.getUser().getDisplayName() + " has won ! GG !");
        }
    }
}
