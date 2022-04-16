#!/bin/bash
# Requires: python3, pip3.
#
# Installs and configures the pre-submit checks found in .pre-commit-config.yaml
# If you'd like to customize your pre-submit checks, see the yaml config.

echo 'export PATH=$PATH:~/.local/bin' >>~/.bashrc
export PATH=$PATH:~/.local/bin
pip3 install pre-commit
pre-commit install
