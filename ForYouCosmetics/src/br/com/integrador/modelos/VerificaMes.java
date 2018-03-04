/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.integrador.modelos;

/**
 *
 * @author Estefania
 */
public class VerificaMes {

    public String verificarMes(String mes) {
        if (mes.equals("Janeiro")) {
            return "01";
        } else {
            if (mes.equals("Fevereiro")) {
                return "02";
            } else {
                if (mes.equals("Março")) {
                    return "03";
                } else {
                    if (mes.equals("Abril")) {
                        return "04";
                    } else {
                        if (mes.equals("Maio")) {
                            return "05";
                        } else {
                            if (mes.endsWith("Junho")) {
                                return "06";
                            } else {
                                if (mes.equals("Jullho")) {
                                    return "07";
                                } else {
                                    if (mes.equals("Agosto")) {
                                        return "08";
                                    } else {
                                        if (mes.equals("Setembro")) {
                                            return "09";
                                        } else {
                                            if (mes.equals("Outubro")) {
                                                return "10";
                                            } else {
                                                if (mes.equals("Novembro")) {
                                                    return "11";
                                                } else {
                                                    if (mes.equals("Dezembro")) {
                                                        return "12";
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return "0";
    }
}
