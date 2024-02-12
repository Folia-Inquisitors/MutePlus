# MutePlus
Website: https://www.spigotmc.org/resources/muteplus.115029/

## Building instructions

mvn clean install
 
## Description

This allows you others to mute people that are annoying and spam chat. This is meant to be a simple plugin for servers. It is highly optimized and heavily tested. Feel free to constribute,.

### Player commands 

> /mute ( player ), 
>
> /unmute ( player ).

### Admin commands 
> /GlobalMute ( player )
>
> /GlobalUnmute ( player ).

## Default Config

```
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
```

## Default Messeges

```
global-mute: '&cYou are muted globally'
player-only: '&cYou must be a player to do this'
prefix: '&8[&cMutePlus&8] &r'
success: '&aSuccess'
yatpa:
  cancel: '&cYou cannot request teleport while muted'
player-not-found: '&cPlayer not found'
command-denied: '&cYou cannot use this command while muted'
```

## Documentation

### Part 1 - When adding external commands do 
> 
> pattern: \\\\ before the /
> 
> so pattern: \\\\/

### Part 2 - When adding a command that uses an username use (.+) 

### Part 3 - When you have a external command include the quotation marks
> 
> an example of using part 1, 2 and 3 together 
>
> "pattern: \\\\/clan invite (.+)" 

### Folia inquisitors

[<img src="https://github.com/Folia-Inquisitors.png" width=80 alt="Folia-Inquisitors">](https://github.com/orgs/Folia-Inquisitors/repositories)
[<img src="https://github.com/HSGamer.png" width=80 alt="HSGamer">](https://github.com/HSGamer)
