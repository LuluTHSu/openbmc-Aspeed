RDEPENDS:${PN}-extras:append:mtjade = " \
                                       webui-vue \
                                       phosphor-image-signing \
                                       phosphor-virtual-sensor \
                                       phosphor-misc-usb-ctrl \
                                     "

RDEPENDS:${PN}-inventory:append:mtjade = " \
                                          fault-monitor \
                                          id-button \
                                          psu-hotswap-reset \
                                          host-gpio-handling \
                                          dbus-sensors \
                                          entity-manager \
                                        "
