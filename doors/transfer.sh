#!/bin/zsh

files=("env.py" "led.py" "wifi.py" "servo.py" "main.py" "cert.py")

source ./.venv/bin/activate

mpremote connect $1 + soft-reset

for file in "${files[@]}"; do
  mpremote cp $file ":$file"
done

mpremote cp -r lib/ :
mpremote cp -r certs/ :

mpremote disconnect + reset
