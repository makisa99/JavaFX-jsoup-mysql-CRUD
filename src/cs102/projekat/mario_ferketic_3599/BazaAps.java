/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs102.projekat.mario_ferketic_3599;

import java.sql.Connection;

/**
 *
 * @author Mario
 */
public abstract class BazaAps {
    protected static Connection conn = null;
    protected static final String url = "jdbc:mysql://localhost/cs102pr_mf";
    protected static final String username = "root";
    protected static final String password = "";
}
