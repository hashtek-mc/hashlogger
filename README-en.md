# 📰 HashLogger

Console debugging utility.

HashLogger is a log-level based logger which permits, at runtime, to filter logs gravity.

[🇫🇷 Egalement disponible en Français !](https://github.com/hashtek-mc/hashlogger/blob/main/README.md)

## Log levels `↗️`

`DEBUG`, `INFO`, `ERROR`, `WARNING`, `CRITICAL`, `FATAL`

## Usage

**Code :**
```java
/*                ⬇️ Mandatory in order to log anything.                      */
public class Test implements HashLoggable {

  HashLogger logger = new HashLogger(this, LogLevel.INFO);
  /*                                  ⬆️ Asked for a more precise targeting.  */

  public static void main(String[] args) {
  /*             ⬇️ Here is the targeting.                                    */
    logger.debug(this, "Debugging log.");
    logger.info(this, "Information log.");
    logger.error(this, "Error log.");
    logger.warning(this, "Warning log.");
    logger.critical(this, "Critical log.");
    logger.fatal(this, "Fatal log.");
  }

}
```
**Console :**
```plaintext
HashLogger instance created. Log level: INFO

[Tekore: Test.java] <INFO> Information log.
[Tekore: Test.java] <ERROR> Error log.
[Tekore: Test.java] <WARNING> Warning log.
[Tekore: Test.java] <CRITICAL> Critical log.
[Tekore: Test.java] <FATAL> Fatal log.
```
`⚠️` It is strongly recommended to have only one instance of `HashLogger` at the root of your program.

## Features

- Timestamp logging.
```java
logger.setShowTimestamp(true);
```
```plaintext
>>> [Tekore: Test.java] (02/02/2024 - 02:42:04) <ERROR> Error log.
```
- Short logging.
```java
logger.setShortDisplay(true);
```
```plaintext
>>> [Tekore: Test.java] <ERR> Error log.
```
- `Exception` stack trace logging
(uniquement pour les niveaux de log `ERROR`, `CRITICAL` et `FATAL`).
```java
...
} catch (Exception exception) {
    logger.critical(this, "Error while fetching data.", exception);
}
```
```plaintext
>>> [HashLogger: SQLManager.java] <CRITICAL> Error while fetching data.
    Exception at fr.hashtek.hashlogger.HashLogger [...]
```

- Colors!

![](https://cdn.discordapp.com/attachments/1201670734095859812/1205493884420100096/image.png?ex=65d892ab&is=65c61dab&hm=1e1aabd11d97d34d2ad2d0ed6e9fe44d25d7dcc1a15d230ba160e353e3eb3dbf&)
![](https://cdn.discordapp.com/attachments/1201670734095859812/1205495547545260042/image.png?ex=65d89437&is=65c61f37&hm=7af97d1e11df338fcb0b40192559ad17ed2d7fda4b26a26bb6587c4e995af6a9&)

## Made with 💜 by [Lysandre B.](https://github.com/Shuvlyy) ・ [![wakatime](https://wakatime.com/badge/user/2f50fe6c-0368-4bef-aa01-3a67193b63f8/project/018d6107-5476-45b0-a472-ef196a0f7de6.svg)](https://wakatime.com/badge/user/2f50fe6c-0368-4bef-aa01-3a67193b63f8/project/018d6107-5476-45b0-a472-ef196a0f7de6)