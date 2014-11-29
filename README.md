GibblerFinal
============

IMPORTANT: If you have edits to the files please upload them to Messenger Project instead of here. Once edits have been confirmed I will reupload the files that were edited so that only one copy stays here. I'm just trying to keep this space clean.

============

SETTING UP YOUR DB

Table set-ups are as follows:

Tables that should be created before first run -

  users
  
    Columns: usernames (VARCHAR(45)), userids(INT), password(VARCHAR(45)), firstName(VARCHAR(45)), lastName(VARCHAR(45))
    
    Before running it is best to insert two starting users - one an admin with userid 1 and one a regular user with id 1000
    or greater
    
    
  posts
  
    Columns: text (VARCHAR(140)), privacy (VARCHAR(45)), username (VARCHAR(45)), date_time (VARCHAR(45)), hashtags
    
    (VARCHAR(140)), groups (VARCHAR(140)), users_tagged (VARCHAR(140)), postid (INT)
    
  groups
  
    Columns: name (VARCHAR(140))
    
    
  admin_codes
  
    Columns: codes (VARCHAR(45))
    
    You need to insert some codes. You can make them single characters or whatever to make testing easier.
    
