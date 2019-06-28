// @GENERATOR:play-routes-compiler
// @SOURCE:/home/efraimfarias/gestaodeprocessos/conf/routes
// @DATE:Thu Jun 27 11:36:21 BRT 2019

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:13
package controllers.javascript {

  // @LINE:24
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:24
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }

  // @LINE:13
  class ReverseResponsavelController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:16
    def updateResponsavel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ResponsavelController.updateResponsavel",
      """
        function(id0) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "responsaveis/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:17
    def deleteResponsavel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ResponsavelController.deleteResponsavel",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "responsaveis/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:15
    def responsavel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ResponsavelController.responsavel",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "responsaveis/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Long]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:14
    def responsaveis: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ResponsavelController.responsaveis",
      """
        function(page0,size1) {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "responsaveis/find-all" + _qS([(page0 == null ? null : (""" + implicitly[play.api.mvc.QueryStringBindable[Long]].javascriptUnbind + """)("page", page0)), (size1 == null ? null : (""" + implicitly[play.api.mvc.QueryStringBindable[Long]].javascriptUnbind + """)("size", size1))])})
        }
      """
    )
  
    // @LINE:13
    def addResponsavel: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.ResponsavelController.addResponsavel",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "responsaveis/"})
        }
      """
    )
  
  }


}
