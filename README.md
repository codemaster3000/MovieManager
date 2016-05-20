# Black Media Manager

Black Media Manager is a movie files manager database written in java. Useable with a simpe

Maven
-----
not available yet on repo

```xml
<dependency>
    <groupId>de.blackmediamanger</groupId>
    <artifactId>blackmediamanager</artifactId>
    <version>0.1</version>
</dependency>
```

Features ToDo List
-------------------

# To do
- [ ] Scan & add media files
    - [ ] scan all folders including subfolder for movie filetypes (mkv, avi,..)
    - [ ] read mkv media informations (MediaInfo.dll api)
    - [ ] fetch TMDB movie infos (tmdb api)
    - [ ] add all available as new database entry
    	- [ ] check if movie already exist in database (quality replace dialog)
- [ ] movie statistics view (longest runtime toplist, oldest movie, biggest movie, genre charts, etc)
- [ ] XREL release infos: show newest BlueRay scene releases
- [ ] movie database view
    - [ ] list all movies (read MySQL database)
    - [ ] show data for selected movie (cover, movieinfos, ...)
    - [ ] edit data for selected movie
    - [ ] filter system (user owned movies, sorting)
    - [ ] TV Series & Documentations added as extra gui views

## Ideas
- [ ] Subtitles search
- [ ] Music files

Build
-------------------------
[![Build Status](http://jenkins.omertron.com/job/YAMJv3/badge/icon)](http://jenkins.omertron.com/job/YAMJv3)

Licence
-------------------------
[![Creative Commons License](http://i.creativecommons.org/l/by-sa/3.0/88x31.png)](http://creativecommons.org/licenses/by-sa/3.0/deed.en_US)
