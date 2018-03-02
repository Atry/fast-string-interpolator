package com.sizmek.fsi.macros

import com.sizmek.fsi._
import org.scalatest.prop.PropertyChecks
import org.scalatest.{FlatSpec, Matchers}

class FastStringInterpolatorSpec extends FlatSpec with Matchers with PropertyChecks {
  "FastStringInterpolator.fs" should "call args exactly once and in order of declaration in a string literal" in {
    var x = 0

    def f(): Int = {
      x += 1
      x
    }

    def g(): Int = {
      x *= 9
      x
    }

    fs"${f()}${g()}" shouldBe "19"
  }
  "FastStringInterpolator.fs" should "build the same string as a simple string interpolator" in {
    fs"${null}${1}${'A'}${"A"}" shouldBe s"${null}${1}${'A'}${"A"}"
    fs"${null}x${1}x${'A'}x${"A"}" shouldBe s"${null}x${1}x${'A'}x${"A"}"
    fs"${null}xx${1}xx${'A'}xx${"A"}" shouldBe s"${null}xx${1}xx${'A'}xx${"A"}"
    fs"[${fs"<${fs"{${fs"${1}".toInt}}"}>"}]" shouldBe s"[${s"<${s"{${s"${1}".toInt}}"}>"}]"
    fs"\b\f\n\t\1\11\111" shouldBe s"\b\f\n\t\1\11\111"
    fs""""""" shouldBe s"""""""
  }
  "FastStringInterpolator.fraw" should "call args exactly once and in order of declaration in a string literal" in {
    var x = 0

    def f(): Int = {
      x += 1
      x
    }

    def g(): Int = {
      x *= 9
      x
    }

    fraw"${f()}${g()}" shouldBe "19"
  }
  "FastStringInterpolator.fraw" should "build the same string as a raw string interpolator" in {
    fraw"${null}${1}${'A'}${"A"}" shouldBe raw"${null}${1}${'A'}${"A"}"
    fraw"${null}x${1}x${'A'}x${"A"}" shouldBe raw"${null}x${1}x${'A'}x${"A"}"
    fraw"${null}xx${1}xx${'A'}xx${"A"}" shouldBe raw"${null}xx${1}xx${'A'}xx${"A"}"
    fraw"[${fraw"<${fraw"{${fraw"${1}".toInt}}"}>"}]" shouldBe raw"[${raw"<${raw"{${raw"${1}".toInt}}"}>"}]"
    fraw"\b\f\n\t\1\11\111" shouldBe raw"\b\f\n\t\1\11\111"
    fraw""""""" shouldBe raw"""""""
  }
}