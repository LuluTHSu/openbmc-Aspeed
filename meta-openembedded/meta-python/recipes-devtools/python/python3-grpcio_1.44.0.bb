DESCRIPTION = "Google gRPC"
HOMEPAGE = "http://www.grpc.io/"
SECTION = "devel/python"
LICENSE = "Apache-2.0 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENSE;md5=6e4cf218112648d22420a84281b68b88"

DEPENDS += "${PYTHON_PN}-protobuf"

SRC_URI += "file://0001-setup.py-Do-not-mix-C-and-C-compiler-options.patch"
SRC_URI:append:class-target = " file://ppc-boringssl-support.patch \
                                file://boring_ssl.patch \
                                file://mips_bigendian.patch \
                                file://0001-absl-always-use-asm-sgidefs.h.patch \
                                file://abseil-ppc-fixes.patch;patchdir=third_party/abseil-cpp \
"
SRC_URI[sha256sum] = "4bae1c99896045d3062ab95478411c8d5a52cb84b91a1517312629fa6cfeb50e"

RDEPENDS:${PN} = "${PYTHON_PN}-protobuf \
                  ${PYTHON_PN}-setuptools \
                  ${PYTHON_PN}-six \
"

inherit setuptools3
inherit pypi

export GRPC_PYTHON_DISABLE_LIBC_COMPATIBILITY = "1"

BORING_SSL_PLATFORM:arm = "linux-arm"
BORING_SSL_PLATFORM:x86-64 = "linux-x86_64"
BORING_SSL_PLATFORM ?= "unsupported"
export GRPC_BORING_SSL_PLATFORM = "${BORING_SSL_PLATFORM}"

BORING_SSL:x86-64 = "1"
BORING_SSL:arm = "1"
BORING_SSL ?= "0"
export GRPC_BUILD_WITH_BORING_SSL_ASM = "${BORING_SSL}"

GRPC_CFLAGS ?= ""
GRPC_CFLAGS:append:toolchain-clang = " -fvisibility=hidden -fno-wrapv -fno-exceptions"
export GRPC_PYTHON_CFLAGS = "${GRPC_CFLAGS}"

CLEANBROKEN = "1"

BBCLASSEXTEND = "native nativesdk"

CCACHE_DISABLE = "1"
