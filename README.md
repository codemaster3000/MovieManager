# Movie File Manager

Movie File Manager is a mediafiles manager written in java.

Dependencies
------------
TMDB api
MediaInfo api

Maven
-----
//<a href="https://search.maven.org/#search%7Cga%7C1%7Ctmdb-java"><img //src="https://img.shields.io/maven-central/v/com.uwetrottmann/tmdb-java.svg?style=flat-square"></a>

to do: maven

```xml
<dependency>
    <groupId>com.moviedb</groupId>
    <artifactId>tmdb-java</artifactId>
    <version>0.1</version>
</dependency>
```


Functions (translate to english)
------------

scant filmdateien (unterordner inkl)
	-> mkv dateiinfos rauslesen
		-> dateigrösse, filmlänge, auflösung, sprachen, subs, audiotracks,
		   bitrate, framerate, codec
	-> tmdb filminfos mit dateiname suchen (filmname, jahr)
		-> bewertung, inhalt, cover (braucht viel platz),

filmdatei zur datenbank hinzufügen
	-> erstes suchresultat als default nehmen
	-> wenn filmdatei in datenbank existiert dann ersetzungsanfrage beginnen
		-> ersetzungsgrund als loginfo mitgeben (besser quali, fehlerhaft , usw)

filmdatenbank oberfläche
	-> sortieren & filtern nach allen verfügbaren daten
	-> zusammenfassungen
		-> anzahl filme, gesamtgrösse, gesamtdauer usw

Serien & Dokus als extra daten

Licence
-------------------------
[![Creative Commons License](http://i.creativecommons.org/l/by-sa/3.0/88x31.png)](http://creativecommons.org/licenses/by-sa/3.0/deed.en_US)
