# GitHub Repo Setup

Follow this walkthrough to setup your GitHub repo.

You can return to this walkthrough anytime by running this command:

```bash
teachme github-setup-walkthrough.md
```

Click the `Start` button to start the walkthrough!

## Getting Started

[GitHub](https://github.com/) is a Git repository host, which means that it's a
website that allows you to store code. This is useful for
[version control](https://en.wikipedia.org/wiki/Version_control), to release
code as open source, or to collaborate with a team. You're going to use GitHub
to store your projects.

## Register

If you already have a GitHub account, you can skip this step.

Go to [GitHub](https://github.com/) and register for an account.

Make sure you use your own email address here, not your SPS account!

## Unlink Google's Repo

Right now, this code still points to Google's repository instead of your
repository. To fix that, you're first going to remove the pointer to Google's
repo.

Make sure you're inside the git repository you just cloned for the rest of
this walkthrough:

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

1.  Enter a repository name. Something like `sps` or `my-portfolio` is a fine
    name.
2.  Set your repo to public. This allows anyone to see your code.
3.  Click the green `create repository` button!

At this point you should have an empty repo.

## Link Your Repo

In your GitHub repo page, find your repo's URL. It should end in `.git`, like
`https://github.com/your-username/your-repo-name.git`. Copy that URL.

To link this directory to your repo, execute this command:

```bash
git remote add origin YOUR_URL_HERE
```

Then to store this directory in your repo, run this command:

```bash
git push -u origin master
```

You should now see this code in your repo on GitHub!

## Add Collaborators

You'll be sending the code you write to your advisor for review, so your advisor
needs access to your repo.

To give your advisor access to your repo, add them as a collaborator.

1.  Navigate to your repo's page in GitHub.
2.  Go to the `Settings` tab.
3.  Select `Collaborators`.
4.  Add your Project Advisor using their GitHub username or email address.

Your advisor can now help with code reviews.

## Require Pull Requests

When you write code, you're going to use **pull requests** to do code reviews.
To prevent accidentally modifying the code without going through a code review,
set your repo to require pull requests.

1.  Go to your GitHub repo page.
2.  Click the `Settings` tab.
3.  Select `Branches`.
4.  Click the `Add rule` button.
5.  Type `master` into the `Branch name pattern` text box.
6.  Select `Require pull request reviews before merging` and `Include
    administrators`.
7.  Click `Create`.
8.  Click `Save changes`.

## Tell git Who You Are

Before you can do anything else with git, you need to tell it who you are, so
it knows who to attribute commits to. First set your email (make sure to use
the same email as your github account, and not your SPS one):

```bash
git config --global user.email "you@example.com"
```

and then your name:

```bash
git config --global user.name "Your Name"
```

## Modify README

To test that everything is connected, modify the `README.md` file.

The `README.md` file contains the content that shows in your repo's GitHub page.
Change the `README.md` file to say "This repo contains [your name]'s portfolio
and SPS projects."

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
for creating a pull request on the SPS website to send your changes to your
advisor for review.

## Congratulations!

<walkthrough-conclusion-trophy></walkthrough-conclusion-trophy>

After your advisor reviews your changes and you merge your pull request, you're
done with the GitHub repo setup!

The next walkthrough will introduce the Google Cloud Shell environment. Run this
command when you're ready:

```bash
teachme ~/software-product-sprint/walkthroughs/week-0-setup/intro-walkthrough.md
```
