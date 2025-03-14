HOMEPAGE = "https://github.com/AspeedTech-BMC/u-boot"
SECTION = "bootloaders"
DEPENDS += "flex-native bison-native"

LICENSE = "GPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"
PE = "1"

BRANCH="aspeed-master-v2019.04"
SRC_URI = "git://github.com/AspeedTech-BMC/u-boot.git;protocol=https;branch=${BRANCH}"
# Tag for v00.04.11
SRCREV = "418822f0e57e287e0a276be72738ad57d3f6ac28"
SRC_URI:append:ast-abr = " file://0001-u-boot-disable-wdt2-for-abr.patch "

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"
do_configure[cleandirs] = "${B}"

# We don't have 'u-boot-initial-env' target in our u-boot version
UBOOT_INITIAL_ENV=""

PV = "v2019.04+git${SRCPV}"

python do_set_local_version() {
    import os
    s = d.getVar("S")
    b = d.getVar("B")
    try:
        os.remove('%s/.scmversion' % s)
        os.remove('%s/.scmversion' % b)
    except:
        pass

    scm_ver = ""

    try:
        res=bb.process.run("git -C %s describe --tags --exact-match" %  s)[0].strip("\n")

        if res.find("devtool") != -1:
            version_ext = bb.process.run("git -C %s rev-parse --verify --short HEAD" % s)[0].strip("\n")
            scm_ver = ('-%s-%s' % res, version_ext)
        else:
            scm_ver = ('-%s' % res)
    except Exception as e:
        version=bb.process.run("git -C %s rev-parse --verify --short HEAD" % s)[0].strip("\n")
        scm_ver = ('-dirty-%s' % version)

    with open("%s/.scmversion" % s, "a") as f:
        f.write(scm_ver)
    with open("%s/.scmversion" % b, "a") as f:
        f.write(scm_ver)
}

addtask set_local_version before do_compile after do_configure
