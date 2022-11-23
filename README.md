# Dark GeoGebra

![Dark GeoGebra - Flat Dark Theme](https://raw.githubusercontent.com/endorh/dark-geogebra/images/flat-dark-demo.png)

This is a fork from [GeoGebra](https://www.geogebra.org/) (https://www.geogebra.org/). The original code from this
project can be found at the official
[GeoGebra mirror repository](https://github.com/geogebra/geogebra) on GitHub.

This fork was created in order to add the following features to the classic Desktop version
of GeoGebra:

- Dark Theme support (using [FlatLaf](https://github.com/JFormDesigner/FlatLaf), and inverting colors from objects/images/icons)
- Java version updated to Java 17 (which means correct scaling on Hi-DPI devices)
- Mnemonics on menus and dialogs (assigned automatically)
- Infinite view dragging (mouse is warped within the view's bounds, like in Blender)
- Hotkey to focus algebraic input bar (F5)

These features were developed purely for my own personal use, as the GeoGebra developers
seem to not have any plans to add them in the near future (in particular, the
[dark theme](https://help.geogebra.org/topic/black-theme-inverse-to-the-default)).
Unfortunately, some people do not consider a dark theme feature an accessibility
requirement for any user interface.

The implementation of these features focuses exclusively on the `desktop`
module and makes no effort to support them in other platforms neither now
nor in the future.

I have made no effort in updating nor running any tests included in the code,
nor any module not required by the `desktop` module.

You may use this fork at your own risk, for non-commercial purposes, and without any
official support from GeoGebra GmbH (nor me). I have decided to share this fork in the
hope that it may be useful to someone in my same situation and for no other purpose.

I do not plan to actively maintain this repository, but I may (or may not) be able
to help you with problems getting it to work if you create an issue.

## License
The use of this project shall be governed by the
[GeoGebra License](https://www.geogebra.org/license) above anything else, as permitted
by applicable laws.

This project itself falls under the category of Non-commercial Use of GeoGebra.
All copyrighted materials, graphics and other resources included in this repository
belong to the GeoGebra GmbH ("GeoGebra"), and are used only for non-commercial purposes.

Any installers provided in this repository are not official nor endorsed by GeoGebra
in any way, and are provided merely for the convenience of people without the means
or ability to build them themselves. You may use, copy, distribute, modify and transmit
them at your own risk, without ANY warranty and for non-commercial purposes only.

The code itself is licensed under the GNU General Public License (version 3 or later),
as published by the Free Software Foundation, as expressed by the
[GeoGebra License](https://www.geogebra.org/license).

The GeoGebra language files, all GeoGebra documentation and all other documentation files
potentially found in this repository, and all GeoGebra user interface image and style
files are used and licensed under the terms of the Creative Commons
Attribution-NonCommercial-ShareAlike license (version 3.0 or later), as expressed by the
[GeoGebra License](https://www.geogebra.org/license).

The Software (and all related materials and resources) are licensed to you without ANY
warranty nor promise of support or future development by the GeoGebra GmbH, the developer
of this fork or any third party.

For more detailed information, see the LICENSE file and the
[GeoGebra License](https://www.geogebra.org/license), in case I haven't linked to it enough.

# GeoGebra

The primary site for [GeoGebra](https://www.geogebra.org)'s source code is
https://dev.geogebra.org/svn/trunk/geogebra/ which
is a read-only Subversion repository.

The GeoGebra team maintains a copy of it
at https://github.com/geogebra/geogebra.

Please read https://www.geogebra.org/license about GeoGebra's licensing.

To learn more on development of GeoGebra (including suggested compilation
steps) please visit https://dev.geogebra.org/, and the official
[GeoGebra mirror repository](https://github.com/geogebra/geogebra) on GitHub.

## Setup the development environment

* Open IntelliJ. If you don't have IntelliJ on your computer yet
  then you can download and install it from [here](https://www.jetbrains.com/idea/download)
* In the menu select `File/New/Project from Version Control/Git`
* In the new window add the following path: `https://github.com/endorh/dark-geogebra.git`
* Click on `Checkout`, select your preferred destination folder, select Java 17 as the SDK,
  click on OK and wait…
* After the project is checked out, select the root folder of the project,
  open the Run Anything tool (Double Ctrl) and run the following command:
  `./gradlew :desktop:run`, as explained below
* After a few minutes the Desktop UI will appear. Note that compiling GeoGebra requires
  the download of a few dependencies. The first compilation will take considerably longer
  as all necessary modules will need to be compiled. Go drink a coffee, take a walk, read
  a book... you get it.

## Running the desktop version (Classic 5)
To start the desktop version from command line, run

```
./gradlew :desktop:run
```
You can also run `./gradlew :desktop:tasks` to list other options.

If you're using IntelliJ (which you should), you may also pick the
`desktop/application/run` task from the Gradle Tool Window, or create a
run configuration for it. 

## Generating a desktop installer for your platform
To generate a desktop installer for the desktop version, run the following command
on the desired platform (Windows, Linux or macOS) (installers can only be generated
on their target platforms):

```
./gradlew :desktop:jpackage
```
The generated installers will be located within the `desktop/build/jpackage` directory.

If you're using IntelliJ (which you should), you may also pick the `desktop/build/jpackage`
task from the Gradle Tool Window.

## Light Theme
If you despise your eye health so much, yes, you can still use the light theme.
Dark GeoGebra provides a flat version of the light theme, as well as the classic
themes from GeoGebra.

<details>
  <summary>Flat Light Theme</summary>

  ![Dark GeoGebra - Flat Light Theme](https://raw.githubusercontent.com/endorh/dark-geogebra/images/flat-light-demo.png)
</details>
