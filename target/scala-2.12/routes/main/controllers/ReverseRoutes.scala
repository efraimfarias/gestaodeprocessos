// @GENERATOR:play-routes-compiler
// @SOURCE:/home/efraimfarias/gestaodeprocessos/conf/routes
// @DATE:Thu Jun 27 11:36:21 BRT 2019

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:13
package controllers {

  // @LINE:24
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:24
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }

  // @LINE:13
  class ReverseResponsavelController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:16
    def updateResponsavel(id:Long): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + "responsaveis/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:17
    def deleteResponsavel(id:Long): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "responsaveis/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:15
    def responsavel(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "responsaveis/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:14
    def responsaveis(page:Long = 0, size:Long = 10): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "responsaveis/find-all" + play.core.routing.queryString(List(if(page == 0) None else Some(implicitly[play.api.mvc.QueryStringBindable[Long]].unbind("page", page)), if(size == 10) None else Some(implicitly[play.api.mvc.QueryStringBindable[Long]].unbind("size", size)))))
    }
  
    // @LINE:13
    def addResponsavel(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "responsaveis/")
    }
  
  }


}
