package com.ltu.m7019e.m7019e_moviedbapp.database

import com.ltu.m7019e.m7019e_moviedbapp.model.Movie

class Movies {
    val list = mutableListOf<Movie>()

    init {
        list.add(
                Movie("Raya and the Last Dragon",
                        "/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                        "2021-03-03",
                        "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and itâs up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people."))
        list.add(
                Movie("Sentinelle",
                        "/fFRq98cW9lTo6di2o4lK1qUAWaN.jpg",
                        "2021-03-05",
                        "Transferred home after a traumatizing combat mission, a highly trained French soldier uses her lethal skills to hunt down the man who hurt her sister."))
        list.add(
                Movie("Zack Snyder's Justice League",
                        "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
                        "2021-03-18",
                        "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions."))
        list.add(
                Movie("Tom & Jerry",
                "/6KErczPBROQty7QoIsaa6wJYXZi.jpg",
                "2021-02-11",
                "Tom the cat and Jerry the mouse get kicked out of their home and relocate to a fancy New York hotel, where a scrappy employee named Kayla will lose her job if she canât evict Jerry before a high-class wedding at the hotel. Her solution? Hiring Tom to get rid of the pesky mouse."))
        list.add(
                Movie("Below Zero",
                        "/dWSnsAGTfc8U27bWsy2RfwZs0Bs.jpg",
                        "2021-01-29",
                        "When a prisoner transfer van is attacked, the cop in charge must fight those inside and outside while dealing with a silent foe: the icy temperatures."))
        
    }
}