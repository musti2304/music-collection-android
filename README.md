
# What is the problem?
You open the Spotify app and browse through the "New Releases". 
You find albums of artists that you 
- never heard of
- are not following
- explicitly added to your "blacklist"
- are not your favorite type of music

Now if you want to find the latest releases of your favorite artist you could do the following:
- You search for your artist
- You see his latest release at the top of the artist page
- Earlier releases will be further down 

You repeat the steps above for each artist that you are following. This is time consuming and can be frustrating.

# What could be the solution?


# Definitions
- Release: Albums, Singles, EPs
- Artist Collection: A list of all the artists you are currently following


# Features

## Client-side
- Show all the releases of artists you are currently following (Must-have)
- Add a release to your Spotify library (Must-have)
- Show all the releases of specific music genres (Could-have)
- Play a snippet of the release in the app 
## Server-side
- Update your artist collection (daily, weekly, etc.) (Must-have)
  - This is done via a cron task 


# Workflow
1. Open App
2. Initialize a database table for persisting the access and refresh tokens (needed for later API requests)
3. Login via Spotify
	- Use SDKs to authorize access to the user
4. Initialize a database table that contains all the artists (id, name) followed by the user
	- Optional: Get all the artists contained in the user library (one artist can be in a playlist but is not followed yet)
	- Optional: Add the artist that is in your playlist but not followed yet to your followed artists
5. Fetch the latest releases of your artists collection (GET request)
	- Optional: Last week, last month, last N months (Sorting)
6. Show the latest releases of your artists collection 
7. Tap on one of the releases (Switching between views)
	- Save the release to your Spotify library (PUT request)
	- Save this artist to your favorite collection (PUT request)
	- Optional: Open the release in the Spotify app
	- Optional: Play a snippet of the release in your app


# Technical Requirements

First, we need a database table to hold our access and refresh tokens. The table could look like this:

| *user_id* | *access_token* | *refresh_token* | *updated* 		  |
| 123	    | 43asd3acffdh   | 4hdfs32132xyc   | 2020-11-23 18:18 |

- Then, we need a database table to hold all the artists we are following. Each user get's his own artist collection.
	- This is better done locally so that each user has it's own local database 

| *user_id* | *artist_id* 		 | *artist_name* |
| 123	    | 34daYer4TGSvser5   |  KAS:ST 		 |








