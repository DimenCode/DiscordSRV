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
/**
 * Class generated by DynamicProxy
 */
package github.scarsz.discordsrv.objects.proxy;

import org.bukkit.plugin.Plugin;

public class AlwaysEnabledPluginDynamicProxy {

    public boolean isEnabled() {
        return true;
    }

    private static final dev.vankka.dynamicproxy.DynamicProxy $DYNAMICPROXY = new dev.vankka.dynamicproxy.DynamicProxy(AlwaysEnabledPluginDynamicProxy.class);

    private org.bukkit.plugin.Plugin $PROXY = null;

    public final org.bukkit.plugin.Plugin getProxy(org.bukkit.plugin.Plugin original) {
        if (original == null)
            throw new java.lang.NullPointerException("original");
        return this.$PROXY != null ? this.$PROXY : (this.$PROXY = (org.bukkit.plugin.Plugin) $DYNAMICPROXY.make(original, this));
    }
}
