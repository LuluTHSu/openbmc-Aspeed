SUMMARY = "Xfce Calender"
SECTION = "x11/application"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=9ac2e7cff1ddaf48b6eab6028f23ef88"
DEPENDS = "gtk+ xfce4-panel libical popt"

inherit xfce-app mime-xdg

SRC_URI += "file://0001-drop-setting-is_utc-member-of-icaltimetype.patch"

SRC_URI[md5sum] = "2b7f5d38cb5c6edbcc65d0f52a742e46"
SRC_URI[sha256sum] = "3cf9aa441ae83c8688865f82217025cdf3ebaa152cce4571777b8c2aa8dd9062"

PACKAGECONFIG ??= ""
PACKAGECONFIG[notify] = "--enable-libnotify,--disable-libnotify,libnotify"

PACKAGES =+ "xfce4-orageclock-plugin"
FILES:${PN} += "${datadir}/dbus-1"
FILES:xfce4-orageclock-plugin = "${libdir}/xfce4/panel/plugins/*.so ${datadir}/xfce4/panel/plugins"
FILES:${PN}-dev += "${libdir}/xfce4/panel/plugins/*.la"
