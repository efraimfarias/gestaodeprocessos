// @GENERATOR:play-routes-compiler
// @SOURCE:/home/efraimfarias/gestaodeprocessos/conf/routes
// @DATE:Thu Jun 27 11:36:21 BRT 2019


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
