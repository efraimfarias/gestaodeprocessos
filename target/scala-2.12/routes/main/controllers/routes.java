// @GENERATOR:play-routes-compiler
// @SOURCE:/home/efraimfarias/gestaodeprocessos/conf/routes
// @DATE:Thu Jun 27 11:36:21 BRT 2019

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseResponsavelController ResponsavelController = new controllers.ReverseResponsavelController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseResponsavelController ResponsavelController = new controllers.javascript.ReverseResponsavelController(RoutesPrefix.byNamePrefix());
  }

}
