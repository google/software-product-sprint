# GitHub Repo Setup

Follow this walkthrough to setup your GitHub repository.

You can return to this walkthrough anytime by running this command:

```bash
teachme ~/software-product-sprint/walkthroughs/week-0-setup/github-setup-walkthrough.md
```

Click the `Start` button to start the walkthrough!

## Getting Started

[Git](https://git-scm.com/) is a
[version control](https://en.wikipedia.org/wiki/Version_control) system, which
lets you track changes to your files and collaborate with others using a
_repository_ (a.k.a. _repo_) to store your projects.

[GitHub](https://github.com/) is a Git repository host. In other words, GitHub
is a website that stores your Git repository so others access it. This is useful
for releasing code as open source, or to collaborate with a team.

You're going to use Git and GitHub to store your projects.

## Register

If you already have a GitHub account, you can skip this step.

Go to [GitHub](https://github.com/) and register for an account.

Make sure you use your own email address here, not your SPS account!

## Unlink Google's Repo

When you ran the command
`git clone https://github.com/google/software-product-sprint.git`, you
downloaded the official repository to your Cloud Shell worksapce.

Right now, this repository still points to Google's GitHub account instead of
yours. To fix that, you're first going to remove the pointer to Google's repo.

Make sure you're inside the git repository you just cloned for the rest of this
walkthrough:

```bash
cd ~/software-product-sprint
```

Now to unlink this directory from Google's repo, execute this command:

```bash
git remote remove origin
```

## Create a GitHub Repository

On [GitHub](https://github.com/), click on the `+` in the top-right and click on
`new repository`. This will take you to a page to create your repo on GitHub.

1.  Enter a repository name. Something like `software-product-sprint` or
    `my-portfolio` is a fine name.
2.  Set your repo to public. This allows anyone to see your code.
3.  Click the green `create repository` button!

At this point you should have an empty repo.

## Link Your Repo

On your new empty repo's page, in the "Quick setup" box, you will find a URL
for your repository; it should start with `https://` and end in `.git`, like
`https://github.com/your-username/your-repo-name.git`. Copy that URL.

To link this local copy to your GitHub project, execute this command:

```bash
git remote add origin YOUR_URL_HERE
```

## Push Your Repo

We will tell the Git CLI to remember your credentials, because you will need to
use a special password for it.  Run

```bash
git config credential.helper store
```

to enable that setting.


Then to store this directory in your repo, run this command:

```bash
git push -u origin main
```

At this point, you will be asked for a username/password. You will use your
Github username, but Github will not accept your normal password.  You must use
a "Personal access token" instead.  To generate one, go to the [Personal access
tokens](https://github.com/settings/tokens) page and click "Generate new
token".  (Be sure to save it someplace safe; if you forget it, you won't be
able to see it again, ane must enter a new one.)  Enter your token as the
password, and the command will complete successfully.

You should now see this code in your repo on GitHub!

## Add Collaborators

You'll be sending the code you write to your advisor for review, so your advisor
needs access to your repo.

To give your advisor access to your repo, add them as a collaborator.

1.  Navigate to your repo's page in GitHub.
1.  Go to the `Settings` tab.
1.  Select `Collaborators`. (*Note*: If you don't see this,
    look for `Manage Access` -> `Invite a Collaborator` instead.)
1.  Add your Project Advisor using their GitHub username or email address.

Your advisor can now help with code reviews.

## Require Pull Requests

When you write code, you're going to use **pull requests** to do code reviews.To
prevent accidentally modifying the code without going through a code review, set
your repo to require pull requests.

1.  Go to your GitHub repo page.
2.  Click the `Settings` tab.
3.  Select `Branches`.
4.  Click the `Add rule` button.
5.  Type `main` into the `Branch name pattern` text box.
6.  Select `Require pull request reviews before merging` and `Include
    administrators`.
7.  Click `Create`.

## Tell git Who You Are

Before you can do anything else with git, you need to tell it who you are, so it
knows who to attribute commits to. First set your email (make sure to use the
same email as your github account, and not your SPS one):

```bash
git config --global user.email "you@example.com"
```

and then your name:

```bash
git config --global user.name "Your Name"
```

## Setup Git Hooks

[Git hooks](https://githooks.com/) are scripts that run before or after a Git
events such as: `commit`. Hooks are used to increase productivity, like
automatically formatting code before commiting it. Setup pre-commit hooks by
running the command from within the `software-product-sprint` directory:

```bash
bash setup-pre-commit.bash
```

This will install the hooks listed in `.pre-submit-config.yaml`, which by
default contains a single check to prevent you from accidentally committing
private keys; don't worry, this doesn't come up until Week 3! You can add
whatever hooks you'd like by updating the `.pre-submit-config.yaml` file and
rerunning the setup script. The framework we use,
[Pre-commit ](https://pre-commit.com/index.html), has more documentation on how
to do this.

## Modify README

To test that everything is connected, modify your
<walkthrough-editor-spotlight spotlightId="navigator" spotlightItem="README.md">README.md</walkthrough-editor-spotlight>
file by double clicking on it. If you can't see line numbers to the left of the text, you're seeing the preview tab rather
than the editing tab. If there's a second tab available, try clicking it. If only a single tab is visible, try double
clicking the <walkthrough-editor-spotlight spotlightId="navigator" spotlightItem="README.md">README.md</walkthrough-editor-spotlight> file again.


This file contains the content that shows in your repo's GitHub page.
Change it to say "This repo contains [your name]'s portfolio and
SPS projects."

The `README.md` file now belongs to you. You should feel free to customize it
and make it your own!

## Push Your Changes

To save your changes to your repo, first create a branch:

```bash
git checkout -b YOUR_BRANCH_NAME
```

(You can use anything you want for `YOUR_BRANCH_NAME`. Something like
`[YourName]Development` is fine.)

Add the `README.md` file to the set of changes you want to upload:

```bash
git add README.md
```

Then create a commit which describes the changes you just made:

```bash
git commit -m "Update the README.md file to describe the repo."
```

Finally, push all of your changes to the shared repo:

```bash
git push origin YOUR_BRANCH_NAME
```

Your changes are now stored in a branch on your repo. Follow the instructions
[here](https://help.github.com/en/github/collaborating-with-issues-and-pull-requests/creating-a-pull-request)
for creating a pull request to send your changes to your advisor for review.

## Congratulations!

<walkthrough-conclusion-trophy></walkthrough-conclusion-trophy>

After your advisor reviews your changes and you merge your pull request, you're
done with the GitHub repo setup!

The next walkthrough will introduce the Google Cloud Shell environment. Run this
command when you're ready:

```bash
teachme ~/software-product-sprint/walkthroughs/week-0-setup/intro-walkthrough.md
```
