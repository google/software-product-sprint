#!/bin/bash
# Requires: python3, pip3.
#
# Installs and configures the pre-submit checks found in .pre-commit-config.yaml
# If you'd like to customize your pre-submit checks, see the yaml config.

pip3 install pre-commit
pre-commit install
