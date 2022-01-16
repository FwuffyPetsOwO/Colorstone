# Colorstone

This mod allows resource packs to modify the colors of redstone in a text file. This mod works on Minecraft versions 1.17 and higher.

## How to Use

In your resource pack, create the directory: **assets/colorstone/colors**

In this directory, place a text file named anything you want, as long as it ends in "**.colorstone**"

The contents of this text file define the colors of redstone for every power level from 0 - 15. Each line in the file is able to define the color for one power level. The information is specified with the following format:

_powerlevel=colorhex_

For example, the following line sets the color of power level 4 to grey:

_4=7f7f7f_

You may change some of the colors, all the colors, or even no colors in this file. If any line contains invalid format, it will be skipped.