---
layout: docs
title: "What's New"
---

## 0.4.1

This is a bug fix release over 0.4.0:

Bug Fixes:

 * [#73](https://github.com/alonsodomin/cron4s/issues/73)/[#80](https://github.com/alonsodomin/cron4s/issues/73):
   Rolling an update to the _day of month_ field could cause setting an invalid date, which will eventually return
   a `None` result from the `next`/`prev` methods. This case is now handled and will propagate a carry over value to
   the _month_ field whenever it happens.
   
Changes:

 * [#81](https://github.com/alonsodomin/cron4s/pull/81): The signature for the `set` method in the `IsDateTime` typeclass
   has been changed to capture errors when setting the values.
 * [#78](https://github.com/alonsodomin/cron4s/pull/78): Upgrading the Scala version to 2.12.2 means that case classes
   do not support anymore having implicit parameter lists. This means that all node class implementations under package
   `cron4s.expr` have been rewritten using normal Scala classes instead of case classes. This means that they can not
   be used anymore in pattern matching and that binary compatibility in their case has been broken.

Version Upgrades:

 * Scala: 2.11.11 / 2.12.2
 * ScalaJS: 0.6.18
 * Scala Java Time: 2.0.0-M12

Please, check migration document for instructions on how to migrate your code to the last version:
 [Migration to 0.4.1](https://alonsodomin.github.io/cron4s/docs/migration/0_4_0.html)

## 0.4.0

This the 4th release of Cron4s, aimed at a more complete implementation of the _CRON Spec_, easier (if possible) to
 use, and with important bug fixes.

Improvements:

 * [#58](https://github.com/alonsodomin/cron4s/pull/58): CRON expressions now support `?` symbols in both _day of month_ 
   and _day of week_ fields. This also means that now expressions must use that symbol in one of those two fields to be valid.
 * Additional parse operations where added to the `Cron` entry point: `parse`, `tryParse` and `unsafeParse`. Good-old
   `Cron("...")` syntax is still supported and it be the preferred one as it's the most concise one.
 * [#70](https://github.com/alonsodomin/cron4s/pull/70): Improved parser error messages.
 * [#60](https://github.com/alonsodomin/cron4s/pull/60): ScalaJS & ScalaJVM support for JSR-310 (aka Java 8 Time) has
   been unified in the `cron4s.lib.javatime` package.
 * [#67](https://github.com/alonsodomin/cron4s/pull/67): Added module with support for MomentJS.
 * [#66](https://github.com/alonsodomin/cron4s/pull/66): `Cron` entry point is exported as a CommonJS module in ScalaJS.

Bug Fixes:
 
 * [#59](https://github.com/alonsodomin/cron4s/issues/59): Carry over from fields _month_ and _day of week_ wasn't
   properly applied to the datetime.
 * [#56](https://github.com/alonsodomin/cron4s/issues/56): The effect of updating a date-time field propagates the
   effect to the predecessor fields, no matter the direction of this effect (forward or backwards in time).
   
Changes:

 * [#63](https://github.com/alonsodomin/cron4s/pull/63): [Scalaz](http://scalaz.org) has been replaced by [Cats](http://www.typelevel.org/cats).
 * [#67](https://github.com/alonsodomin/cron4s/pull/67): Joda Time support has been moved to it's own module.
 * [#61](https://github.com/alonsodomin/cron4s/pull/61): Field expression selection by field type is now fully type-checked
   during compilation.
   
Version Upgrades:

 * ScalaJS: 0.6.15
 * Cats: 0.9.0
 * Scala Java Time: 2.0.0-M10
   
Please, check migration document for instructions on how to migrate your code to the last version:
 [Migration to 0.4.0](https://alonsodomin.github.io/cron4s/docs/migration/0_4_0.html) 