/*
 * Copyright 2017 Antonio Alonso Dominguez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cron4s.validation

import cron4s.expr.{EnumerableNode, SeveralNode}
import cron4s.{CronField, CronUnit, FieldError}
import cron4s.testkit.gen.NodeGenerators
import cron4s.types.Enumerated

import org.scalatest.{Matchers, PropSpec}
import org.scalatest.prop.GeneratorDrivenPropertyChecks

/**
  * Created by alonsodomin on 29/12/2016.
  */
class SeveralNodeValidatorSpec extends PropSpec
  with GeneratorDrivenPropertyChecks
  with Matchers
  with NodeGenerators {

  import CronField._

  private[this] def check[F <: CronField](implicit unit: CronUnit[F], ev: Enumerated[CronUnit[F]]): Unit = {
    property(s"SeveralNode[${unit.field}] with valid components should pass validation") {
      forAll(severalGen[F]) { node =>
        NodeValidator[SeveralNode[F]].validate(node) shouldBe List.empty[FieldError]
      }
    }

    property(s"SeveralNode[${unit.field}] with invalid members should contain the errors of its elements") {
      forAll(invalidSeveralGen[F]) { node =>
        val expectedMemberErrors = node.values.list.toList.view.flatMap(NodeValidator[EnumerableNode[F]].validate)
        NodeValidator[SeveralNode[F]].validate(node) should contain allElementsOf expectedMemberErrors
      }
    }
  }

  check[Second]
  check[Minute]
  check[Hour]
  check[DayOfMonth]
  check[Month]
  check[DayOfWeek]

}