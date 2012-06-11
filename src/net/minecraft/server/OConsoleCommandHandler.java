package net.minecraft.server;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

import net.canarymod.Canary;
import net.canarymod.Logman;
import net.canarymod.api.world.CanaryDimension;
import net.canarymod.api.world.Dimension;
import net.canarymod.api.world.World;
import net.minecraft.server.OEntityPlayerMP;
import net.minecraft.server.OICommandListener;
import net.minecraft.server.OIProgressUpdate;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMinecraftServer;
import net.minecraft.server.OPacket;
import net.minecraft.server.OPacket3Chat;
import net.minecraft.server.OPacket70Bed;
import net.minecraft.server.OServerCommand;
import net.minecraft.server.OServerConfigurationManager;
import net.minecraft.server.OWorldServer;
import net.minecraft.server.OWorldSettings;

public class OConsoleCommandHandler {

   private static Logger a = Logger.getLogger("Minecraft");
   private OMinecraftServer b;


   public OConsoleCommandHandler(OMinecraftServer var1) {
      super();
      this.b = var1;
   }

   public synchronized void a(OServerCommand var1) {
      String var2 = var1.a;
      String[] var3 = var2.split(" ");
      String var4 = var3[0];
      String var5 = var2.substring(var4.length()).trim();
      OICommandListener var6 = var1.b;
      String var7 = var6.d();
      // CanaryMod note: reference to configuration manager
      OServerConfigurationManager var8 = this.b.h;
      if(!var4.equalsIgnoreCase("help") && !var4.equalsIgnoreCase("?")) {
         if(var4.equalsIgnoreCase("list")) {
            var6.b("Connected players: " + var8.c());
         } else if(var4.equalsIgnoreCase("stop")) {
            this.a(var7, "Stopping the server..");
            this.b.a();
         } else {
            int var9;
            OWorldServer var10;
            if(var4.equalsIgnoreCase("save-all")) {
               this.a(var7, "Forcing save..");
               if(var8 != null) {
                  var8.g();
               }

               //CanaryMod Changes to process multiple worlds
               for(World w : Canary.getServer().getWorldManager().getAllWorlds()) {
                   for(Dimension dim : w.getDimensions()) {
                       var10 = (OWorldServer) ((CanaryDimension)dim).getHandle();
                       boolean var11 = var10.I;
                       var10.I = false;
                       try {
                           var10.a(true, (OIProgressUpdate)null);
                       } catch (IOException e1) {
                           Logman.logStackTrace("Failed to save Dimension" + dim.getType().name() + " in World " +dim.getName(), e1);
                       }
                       var10.I = var11;
                   }
               }
               //CanaryMod end

               this.a(var7, "Save complete.");
            } else if(var4.equalsIgnoreCase("save-off")) {
               this.a(var7, "Disabling level saving..");

               //CanaryMod Changes to process multiple worlds
               
               for(World w : Canary.getServer().getWorldManager().getAllWorlds()) {
                   for(Dimension dim : w.getDimensions()) {
                       var10 = (OWorldServer) ((CanaryDimension)dim).getHandle();
                       var10.I = true;
                   }
               }
               //CanaryMod end
            } else if(var4.equalsIgnoreCase("save-on")) {
               this.a(var7, "Enabling level saving..");
               
             //CanaryMod Changes to process multiple worlds
               for(World w : Canary.getServer().getWorldManager().getAllWorlds()) {
                   for(Dimension dim : w.getDimensions()) {
                       var10 = (OWorldServer) ((CanaryDimension)dim).getHandle();
                       var10.I = false;
                   }
               }
             //CanaryMod end
            } else if(var4.equalsIgnoreCase("op")) {
               var8.op(var5);
               this.a(var7, "Opping " + var5);
               var8.a(var5, "\u00a7eYou are now op!");
            } else if(var4.equalsIgnoreCase("deop")) {
               var8.deop(var5);
               var8.a(var5, "\u00a7eYou are no longer op!");
               this.a(var7, "De-opping " + var5);
            } else if(var4.equalsIgnoreCase("ban-ip")) {
               var8.c(var5);
               this.a(var7, "Banning ip " + var5);
            } else if(var4.equalsIgnoreCase("pardon-ip")) {
               var8.d(var5);
               this.a(var7, "Pardoning ip " + var5);
            } else {
               OEntityPlayerMP var18;
               if(var4.equalsIgnoreCase("ban")) {
                  var8.a(var5);
                  this.a(var7, "Banning " + var5);
                  var18 = var8.i(var5);
                  if(var18 != null) {
                     var18.a.a("Banned by admin");
                  }
               } else if(var4.equalsIgnoreCase("pardon")) {
                  var8.b(var5);
                  this.a(var7, "Pardoning " + var5);
               } else {
                  String var19;
                  int var21;
                  if(var4.equalsIgnoreCase("kick")) {
                     var19 = var5;
                     var18 = null;

                     for(var21 = 0; var21 < var8.b.size(); ++var21) {
                        OEntityPlayerMP var12 = (OEntityPlayerMP)var8.b.get(var21);
                        if(var12.v.equalsIgnoreCase(var19)) {
                           var18 = var12;
                        }
                     }

                     if(var18 != null) {
                        var18.a.a("Kicked by admin");
                        this.a(var7, "Kicking " + var18.v);
                     } else {
                        var6.b("Can\'t find user " + var19 + ". No kick.");
                     }
                  } else if(var4.equalsIgnoreCase("tp")) {
                     if(var3.length == 3) {
                        OEntityPlayerMP var20 = var8.i(var3[1]);
                        var18 = var8.i(var3[2]);
                        if(var20 == null) {
                           var6.b("Can\'t find user " + var3[1] + ". No tp.");
                        } else if(var18 == null) {
                           var6.b("Can\'t find user " + var3[2] + ". No tp.");
                        } else if(var20.w != var18.w) {
                           var6.b("User " + var3[1] + " and " + var3[2] + " are in different dimensions. No tp.");
                        } else {
                           var20.a.a(var18.bm, var18.bn, var18.bo, var18.bs, var18.bt);
                           this.a(var7, "Teleporting " + var3[1] + " to " + var3[2] + ".");
                        }
                     } else {
                        var6.b("Syntax error, please provide a source and a target.");
                     }
                  } else if(var4.equalsIgnoreCase("give")) {
                     if(var3.length != 3 && var3.length != 4 && var3.length != 5) {
                        return;
                     }

                     var19 = var3[1];
                     var18 = var8.i(var19);
                     if(var18 != null) {
                        try {
                           var21 = Integer.parseInt(var3[2]);
                           if(OItem.d[var21] != null) {
                              this.a(var7, "Giving " + var18.v + " some " + var21);
                              int var22 = 1;
                              int var13 = 0;
                              if(var3.length > 3) {
                                 var22 = this.a(var3[3], 1);
                              }

                              if(var3.length > 4) {
                                 var13 = this.a(var3[4], 1);
                              }

                              if(var22 < 1) {
                                 var22 = 1;
                              }

                              if(var22 > 64) {
                                 var22 = 64;
                              }

                              var18.b(new OItemStack(var21, var22, var13));
                           } else {
                              var6.b("There\'s no item with id " + var21);
                           }
                        } catch (NumberFormatException var16) {
                           var6.b("There\'s no item with id " + var3[2]);
                        }
                     } else {
                        var6.b("Can\'t find user " + var19);
                     }
                  } else if(var4.equalsIgnoreCase("xp")) {
                     if(var3.length != 3) {
                        return;
                     }

                     var19 = var3[1];
                     var18 = var8.i(var19);
                     if(var18 != null) {
                        try {
                           var21 = Integer.parseInt(var3[2]);
                           var21 = var21 > 5000?5000:var21;
                           this.a(var7, "Giving " + var21 + " orbs to " + var18.v);
                           var18.g(var21);
                        } catch (NumberFormatException var15) {
                           var6.b("Invalid orb count: " + var3[2]);
                        }
                     } else {
                        var6.b("Can\'t find user " + var19);
                     }
                  } else if(var4.equalsIgnoreCase("gamemode")) {
                     if(var3.length != 3) {
                        return;
                     }

                     var19 = var3[1];
                     var18 = var8.i(var19);
                     if(var18 != null) {
                        try {
                           var21 = Integer.parseInt(var3[2]);
                           var21 = OWorldSettings.a(var21);
                           if(var18.c.a() != var21) {
                              this.a(var7, "Setting " + var18.v + " to game mode " + var21);
                              var18.c.a(var21);
                              var18.a.b((OPacket)(new OPacket70Bed(3, var21)));
                           } else {
                              this.a(var7, var18.v + " already has game mode " + var21);
                           }
                        } catch (NumberFormatException var14) {
                           var6.b("There\'s no game mode with id " + var3[2]);
                        }
                     } else {
                        var6.b("Can\'t find user " + var19);
                     }
                  } else if(var4.equalsIgnoreCase("time")) {
                     if(var3.length != 3) {
                        return;
                     }

                     var19 = var3[1];

                     try {
                        int var23 = Integer.parseInt(var3[2]);
                        OWorldServer var24;
                        if("add".equalsIgnoreCase(var19)) {
                            //CanaryMod Changes for multiworld
                            for(World w : Canary.getServer().getWorldManager().getAllWorlds()) {
                                for(Dimension dim : w.getDimensions()) {
                                    var24 = (OWorldServer) ((CanaryDimension)dim).getHandle();
                                    var24.b(var24.o() + (long)var23);
                                }
                           }
                            //CanaryMod end

                           this.a(var7, "Added " + var23 + " to time");
                        } else if("set".equalsIgnoreCase(var19)) {
                            //CanaryMod changes for mutiworld
                            for(World w : Canary.getServer().getWorldManager().getAllWorlds()) {
                                for(Dimension dim : w.getDimensions()) {
                                    var24 = (OWorldServer) ((CanaryDimension)dim).getHandle();
                                    var24.b((long)var23);
                                }
                           }

                           this.a(var7, "Set time to " + var23);
                        } else {
                           var6.b("Unknown method, use either \"add\" or \"set\"");
                        }
                     } catch (NumberFormatException var17) {
                        var6.b("Unable to convert time value, " + var3[2]);
                     }
                  } else if(var4.equalsIgnoreCase("say") && var5.length() > 0) {
                     a.info("[" + var7 + "] " + var5);
                     var8.sendPacketToAll((OPacket)(new OPacket3Chat("\u00a7d[Server] " + var5)));
                  } else if(var4.equalsIgnoreCase("tell")) {
                     if(var3.length >= 3) {
                        var2 = var2.substring(var2.indexOf(" ")).trim();
                        var2 = var2.substring(var2.indexOf(" ")).trim();
                        a.info("[" + var7 + "->" + var3[1] + "] " + var2);
                        var2 = "\u00a77" + var7 + " whispers " + var2;
                        a.info(var2);
                        if(!var8.a(var3[1], (OPacket)(new OPacket3Chat(var2)))) {
                           var6.b("There\'s no player by that name online.");
                        }
                     }
                  } else if(var4.equalsIgnoreCase("whitelist")) {
                     this.a(var7, var2, var6);
                  } else if(var4.equalsIgnoreCase("toggledownfall")) {
                      //CanaryMod changes to switch weather in all worlds //TODO: Chris: Must be disabled I would say
                      for(World w : Canary.getServer().getWorldManager().getAllWorlds()) {
                          ((CanaryDimension)w.getNormal()).getHandle().j(); //sets a property...
                      }
//                     this.b.worldServer[0].j();
                     var6.b("Toggling rain and snow, hold on...");
                  } else if(var4.equalsIgnoreCase("banlist")) {
                     if(var3.length == 2) {
                        if(var3[1].equals("ips")) {
                           var6.b("IP Ban list:" + this.a(this.b.q(), ", "));
                        }
                     } else {
                        var6.b("Ban list:" + this.a(this.b.r(), ", "));
                     }
                  } else {
                     a.info("Unknown console command. Type \"help\" for help.");
                  }
               }
            }
         }
      } else {
         this.a(var6);
      }

   }

   private void a(String var1, String var2, OICommandListener var3) {
      String[] var4 = var2.split(" ");
      if(var4.length >= 2) {
         String var5 = var4[1].toLowerCase();
         if("on".equals(var5)) {
            this.a(var1, "Turned on white-listing");
            this.b.d.b("white-list", true); // CanaryMod note: sets the white-list property to false
         } else if("off".equals(var5)) {
            this.a(var1, "Turned off white-listing");
            this.b.d.b("white-list", false); // CanaryMod note: sets the white-list property to false
         } else if("list".equals(var5)) {
            Set var6 = this.b.h.h();
            String var7 = "";

            String var9;
            for(Iterator var8 = var6.iterator(); var8.hasNext(); var7 = var7 + var9 + " ") {
               var9 = (String)var8.next();
            }

            var3.b("White-listed players: " + var7);
         } else {
            String var10;
            if("add".equals(var5) && var4.length == 3) {
               var10 = var4[2].toLowerCase();
               this.b.h.k(var10);
               this.a(var1, "Added " + var10 + " to white-list");
            } else if("remove".equals(var5) && var4.length == 3) {
               var10 = var4[2].toLowerCase();
               this.b.h.l(var10);
               this.a(var1, "Removed " + var10 + " from white-list");
            } else if("reload".equals(var5)) {
               this.b.h.i();
               this.a(var1, "Reloaded white-list from file");
            }
         }

      }
   }

   private void a(OICommandListener var1) {
      var1.b("To run the server without a gui, start it like this:");
      var1.b("   java -Xmx1024M -Xms1024M -jar minecraft_server.jar nogui");
      var1.b("Console commands:");
      var1.b("   help  or  ?               shows this message");
      var1.b("   kick <player>             removes a player from the server");
      var1.b("   ban <player>              bans a player from the server");
      var1.b("   pardon <player>           pardons a banned player so that they can connect again");
      var1.b("   ban-ip <ip>               bans an IP address from the server");
      var1.b("   pardon-ip <ip>            pardons a banned IP address so that they can connect again");
      var1.b("   op <player>               turns a player into an op");
      var1.b("   deop <player>             removes op status from a player");
      var1.b("   tp <player1> <player2>    moves one player to the same location as another player");
      var1.b("   give <player> <id> [num]  gives a player a resource");
      var1.b("   tell <player> <message>   sends a private message to a player");
      var1.b("   stop                      gracefully stops the server");
      var1.b("   save-all                  forces a server-wide level save");
      var1.b("   save-off                  disables terrain saving (useful for backup scripts)");
      var1.b("   save-on                   re-enables terrain saving");
      var1.b("   list                      lists all currently connected players");
      var1.b("   say <message>             broadcasts a message to all players");
      var1.b("   time <add|set> <amount>   adds to or sets the world time (0-24000)");
      var1.b("   gamemode <player> <mode>  sets player\'s game mode (0 or 1)");
      var1.b("   toggledownfall            toggles rain on or off");
      var1.b("   xp <player> <amount>      gives the player the amount of xp (0-5000)");
   }

   private void a(String var1, String var2) {
      String var3 = var1 + ": " + var2;
      this.b.h.j("\u00a77(" + var3 + ")");
      a.info(var3);
   }

   private int a(String var1, int var2) {
      try {
         return Integer.parseInt(var1);
      } catch (NumberFormatException var4) {
         return var2;
      }
   }

   private String a(String[] var1, String var2) {
      int var3 = var1.length;
      if(0 == var3) {
         return "";
      } else {
         StringBuilder var4 = new StringBuilder();
         var4.append(var1[0]);

         for(int var5 = 1; var5 < var3; ++var5) {
            var4.append(var2).append(var1[var5]);
         }

         return var4.toString();
      }
   }

}
