project('aspeed-pfr-tool', 'c', 'cpp',
        version : '1.0',
        meson_version: '>=0.57.0',
        )

openssl = dependency('openssl', required : true)
# i2c-tools doesn't ship a pkg-config file for libi2c
i2c = meson.get_compiler('c').find_library('i2c')
aspeed_pfr_tool_dependencies = [openssl, i2c]

# Include Directories
incdir = include_directories(
    'include'
)

# Generate the aspeed-pfr-tool executable

executable(
    'aspeed-pfr-tool',
    [
      'i2c_utils.c',
      'provision.c',
      'checkpoint.c',
      'main.c'
    ],
    include_directories : incdir,
    dependencies: aspeed_pfr_tool_dependencies,
    install: true
)

# PFR Info
# Gather the Configuration data
conf_data = configuration_data()

conf_data.set('I2C_BUS', get_option('i2c_bus'))
conf_data.set('ROT_ADDRESS', get_option('rot_address'))
conf_data.set('BMC_ACTIVE_PFM_OFFSET', get_option('bmc_active_pfm_offset'))
conf_data.set('BMC_STAGING_OFFSET', get_option('bmc_staging_offset'))
conf_data.set('BMC_RECOVERY_OFFSET', get_option('bmc_recovery_offset'))
conf_data.set('PCH_ACTIVE_PFM_OFFSET', get_option('pch_active_pfm_offset'))
conf_data.set('PCH_STAGING_OFFSET', get_option('pch_staging_offset'))
conf_data.set('PCH_RECOVERY_OFFSET', get_option('pch_recovery_offset'))

summary('I2C_BUS',get_option('i2c_bus'), section : 'PFR Info')
summary('ROT_ADDRESS',get_option('rot_address'), section : 'PFR Info')
summary('BMC_ACTIVE_PFM_OFFSET',get_option('bmc_active_pfm_offset'), section : 'PFR Info')
summary('BMC_STAGING_OFFSET',get_option('bmc_staging_offset'), section : 'PFR Info')
summary('BMC_RECOVERY_OFFSET',get_option('bmc_recovery_offset'), section : 'PFR Info')
summary('PCH_ACTIVE_PFM_OFFSET',get_option('pch_active_pfm_offset'), section : 'PFR Info')
summary('PCH_STAGING_OFFSET',get_option('pch_staging_offset'), section : 'PFR Info')
summary('PCH_RECOVERY_OFFSET',get_option('pch_recovery_offset'), section : 'PFR Info')

# Copy aspeed-pfr-tool.conf to /usr/share/pfrconfig
configure_file(input : 'aspeed-pfr-tool.conf.in',
               output : 'aspeed-pfr-tool.conf',
               configuration: conf_data,
               install_dir: '/usr/share/pfrconfig',
               install : true)
