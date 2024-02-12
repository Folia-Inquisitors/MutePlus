# MutePlus
Website: https://www.spigotmc.org/resources/muteplus.115029/

## Building instructions

mvn clean install
 
## Description

This allows you others to mute people that are annoying and spam chat. This is meant to be a simple plugin for servers. It is highly optimized and heavily tested. Feel free to constribute,.

## Commands

Player commands - /mute ( player ), /unmute ( player ).

Admin commands - /GlobalMute ( player ), /GlobalUnmute ( player ).

## Default Config

filter-command:
  global:
  - /r
  - /reply
  player:
  - /tpa
  - /tpahere
  - /w
  - /whisper
  - /msg
  - /tell
  enabled: true

## Documentation

### Part1 - When adding external commands do 
> 
> pattern: \\ before the /
> so pattern: \\/

### Part 2 - When adding a command that uses an username use (.+) 

### Part 3 - When you have a external command include the quotation marks
> 
> an example of using part 1 and 2 together 
>
> "pattern: \\/clan invite (.+)" 

### Folia inquisitors

[<img src="https://github.com/Folia-Inquisitors.png" width=80 alt="Folia-Inquisitors">](https://github.com/orgs/Folia-Inquisitors/repositories)
[<img src="https://github.com/HSGamer.png" width=80 alt="HSGamer">](https://github.com/HSGamer)
