// @GENERATOR:play-routes-compiler
// @SOURCE:/home/efraimfarias/gestaodeprocessos/conf/routes
// @DATE:Thu Jun 27 11:36:21 BRT 2019

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:13
  ResponsavelController_1: controllers.ResponsavelController,
  // @LINE:24
  Assets_0: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:13
    ResponsavelController_1: controllers.ResponsavelController,
    // @LINE:24
    Assets_0: controllers.Assets
  ) = this(errorHandler, ResponsavelController_1, Assets_0, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, ResponsavelController_1, Assets_0, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """responsaveis/""", """controllers.ResponsavelController.addResponsavel"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """responsaveis/find-all""", """controllers.ResponsavelController.responsaveis(page:Long ?= 0, size:Long ?= 10)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """responsaveis/""" + "$" + """id<[^/]+>""", """controllers.ResponsavelController.responsavel(id:Long)"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """responsaveis/""" + "$" + """id<[^/]+>""", """controllers.ResponsavelController.updateResponsavel(id:Long)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """responsaveis/""" + "$" + """id<[^/]+>""", """controllers.ResponsavelController.deleteResponsavel(id:Long)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:13
  private[this] lazy val controllers_ResponsavelController_addResponsavel0_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("responsaveis/")))
  )
  private[this] lazy val controllers_ResponsavelController_addResponsavel0_invoker = createInvoker(
    ResponsavelController_1.addResponsavel,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ResponsavelController",
      "addResponsavel",
      Nil,
      "POST",
      this.prefix + """responsaveis/""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_ResponsavelController_responsaveis1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("responsaveis/find-all")))
  )
  private[this] lazy val controllers_ResponsavelController_responsaveis1_invoker = createInvoker(
    ResponsavelController_1.responsaveis(fakeValue[Long], fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ResponsavelController",
      "responsaveis",
      Seq(classOf[Long], classOf[Long]),
      "POST",
      this.prefix + """responsaveis/find-all""",
      """""",
      Seq()
    )
  )

  // @LINE:15
  private[this] lazy val controllers_ResponsavelController_responsavel2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("responsaveis/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ResponsavelController_responsavel2_invoker = createInvoker(
    ResponsavelController_1.responsavel(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ResponsavelController",
      "responsavel",
      Seq(classOf[Long]),
      "GET",
      this.prefix + """responsaveis/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_ResponsavelController_updateResponsavel3_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("responsaveis/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ResponsavelController_updateResponsavel3_invoker = createInvoker(
    ResponsavelController_1.updateResponsavel(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ResponsavelController",
      "updateResponsavel",
      Seq(classOf[Long]),
      "PUT",
      this.prefix + """responsaveis/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:17
  private[this] lazy val controllers_ResponsavelController_deleteResponsavel4_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("responsaveis/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_ResponsavelController_deleteResponsavel4_invoker = createInvoker(
    ResponsavelController_1.deleteResponsavel(fakeValue[Long]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ResponsavelController",
      "deleteResponsavel",
      Seq(classOf[Long]),
      "DELETE",
      this.prefix + """responsaveis/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:24
  private[this] lazy val controllers_Assets_versioned5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned5_invoker = createInvoker(
    Assets_0.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:13
    case controllers_ResponsavelController_addResponsavel0_route(params@_) =>
      call { 
        controllers_ResponsavelController_addResponsavel0_invoker.call(ResponsavelController_1.addResponsavel)
      }
  
    // @LINE:14
    case controllers_ResponsavelController_responsaveis1_route(params@_) =>
      call(params.fromQuery[Long]("page", Some(0)), params.fromQuery[Long]("size", Some(10))) { (page, size) =>
        controllers_ResponsavelController_responsaveis1_invoker.call(ResponsavelController_1.responsaveis(page, size))
      }
  
    // @LINE:15
    case controllers_ResponsavelController_responsavel2_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ResponsavelController_responsavel2_invoker.call(ResponsavelController_1.responsavel(id))
      }
  
    // @LINE:16
    case controllers_ResponsavelController_updateResponsavel3_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ResponsavelController_updateResponsavel3_invoker.call(ResponsavelController_1.updateResponsavel(id))
      }
  
    // @LINE:17
    case controllers_ResponsavelController_deleteResponsavel4_route(params@_) =>
      call(params.fromPath[Long]("id", None)) { (id) =>
        controllers_ResponsavelController_deleteResponsavel4_invoker.call(ResponsavelController_1.deleteResponsavel(id))
      }
  
    // @LINE:24
    case controllers_Assets_versioned5_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned5_invoker.call(Assets_0.versioned(path, file))
      }
  }
}
