# Intro

This walkthrough introduces the Google Cloud Shell environment, which you'll be
using throughout SPS.

You can return to this walkthrough anytime by executing this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-0-setup/intro-walkthrough.md
```

Let's get started!

## Google Cloud Shell

[Google Cloud](https://cloud.google.com/) is a set of tools that lets you run
code on the same computers that run Google. There are many ways to use Google
Cloud: as a Java library, as a standalone tool, or as a service.

[Cloud Shell](https://cloud.google.com/shell/) is one way to interact with
Google Cloud. Cloud Shell gives you a
[code editor](https://cloud.google.com/shell/docs/viewing-and-editing-files)
that lets you edit files, and a command line window to run commands. You can
think of Cloud Shell as a window into a computer running inside of Google Cloud.
You're going to use Cloud Shell over the next few weeks to write, build, and run
your code.

If you see `Cloud Shell` in the upper-left corner and you're reading this text
in a panel on the right, then you're already using Cloud Shell!

## Cloud Shell Editor

Cloud Shell comes with a built-in code editor that allows you to browse file
directories and view and edit files.

The editor displays the directory structure in its left-hand panel. You'll learn
more about these files throughout SPS, but for now try clicking around to
explore. Try opening the
<walkthrough-editor-open-file
    filePath="software-product-sprint/walkthroughs/week-0-setup/intro-walkthrough.md">
  intro-walkthrough.md
</walkthrough-editor-open-file>
file to view the source for this walkthrough!

You'll be using the Cloud Shell editor quite a bit in the coming weeks, so take
some time to familiarize yourself now. You can read more about Cloud Shell
Editor [here](https://cloud.google.com/shell/docs/viewing-and-editing-files).

## Command Line

Cloud Shell also comes with a command line console that lets you run commands in
Google Cloud.

You can type commands into the console to run programs, compile your code, and
deploy to Google Cloud.

For now, try a few of these commands:

To get the path of your current directory:

```bash
pwd
```

To view the contents of your current directory:

```bash
ls
```

To move to a different directory:

```bash
cd portfolio
```

To go back up a directory:

```bash
cd ..
```

If you're new to the command line, that's okay! You'll learn about some other
commands soon, and you can also Google "command line commands" for some other
resources.

## Walkthroughs

This repo is split up into directories that contain the projects you'll be
working on each week.

Wherever you see a file that ends with `-walkthrough.md`, you can run the
`teachme` command on that file to open it in the walkthrough panel. For example,
this command runs the walkthrough you're currently reading:

```bash
teachme ~/software-product-sprint/walkthroughs/week-0-setup/intro-walkthrough.md
```

You'll use the `teachme` command to run walkthroughs each week.

## The `portfolio` Directory

The `portfolio` directory contains a bare-bones web app that you'll use to create
your own personal portfolio page! **This is your code** so please make it your
own.

You'll get more specific instructions during week 1, but for now, start
exploring the directory using the Google Cloud Shell editor and the `cd`
command.

## The `walkthroughs` Directory

The `walkthroughs` directory contains the projects you'll be working on over the
next few weeks. Each subdirectory introduces a new concept and includes
examples. You'll use these to build your portfolio page!

You can peek at the files now to get an idea of what you'll be doing, and you'll
get more specific instructions each week.

## Congratulations!

<walkthrough-conclusion-trophy></walkthrough-conclusion-trophy>

You finished this introduction!

Next, you'll write HTML and JavaScript to create a portfolio page, and you'll
deploy it to App Engine so anyone can visit it.

When you're ready, start the next walkthrough by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-1-web-development/portfolio-walkthrough.md
```
