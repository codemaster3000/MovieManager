# Black Media Manager

Black Media Manager is a mediafiles manager written in java.

##Dependencies
TMDB api,
MediaInfo api

##Maven
### Compile
`mvn clean compile assembly:single`

##Functions (translate to english)

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

##Licence
[![Creative Commons License](http://i.creativecommons.org/l/by-sa/3.0/88x31.png)](http://creativecommons.org/licenses/by-sa/3.0/deed.en_US)
