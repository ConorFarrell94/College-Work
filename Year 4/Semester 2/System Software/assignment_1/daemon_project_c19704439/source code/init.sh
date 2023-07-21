#!/bin/bash

cd /home/conor/Documents/daemon_project/

make clean

make

./daemon

echo "[Unit]
Description=daemon
After=network.target

[Service]
Type=simple
ExecStart=/home/conor/Documents/daemon_project/daemon.exe
Restart=always

[Install]
WantedBy=multi-user.target" > /etc/systemd/system/daemon.service

systemctl daemon-reload

systemctl enable daemon.service
