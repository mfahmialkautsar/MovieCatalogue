package mfahmialkautsar.moviecatalogue.utils;

import java.util.ArrayList;

import mfahmialkautsar.moviecatalogue.R;
import mfahmialkautsar.moviecatalogue.data.source.local.entity.MovieEntity;
import mfahmialkautsar.moviecatalogue.data.source.local.entity.TvEntity;
import mfahmialkautsar.moviecatalogue.data.source.remote.response.FilmResponse;

public class FakeDataDummy {

    public static ArrayList<MovieEntity> generateDummyMovie() {
        ArrayList<MovieEntity> movies = new ArrayList<>();

        movies.add(new MovieEntity("m1",
                "Fantastic Beasts: The Crimes of Grindelwald",
                "The second installment of the \"Fantastic Beasts\" series featuring the adventures of Magizoologist Newt Scamander.",
                "November 14, 2018",
                "Drama/Fantasy",
                "2h 13m",
                6.6,
                R.drawable.poster_crimes,
                R.drawable.backdrop_fbeast,
                null));
        movies.add(new MovieEntity("m2",
                "Alita: Battle Angel",
                "Set several centuries in the future, the abandoned Alita is found in the scrapyard of Iron City by Ido, a compassionate cyber-doctor who takes the unconscious cyborg Alita to his clinic. When Alita awakens, she has no memory of who she is, nor does she have any recognition of the world she finds herself in. As Alita learns to navigate her new life and the treacherous streets of Iron City, Ido tries to shield her from her mysterious past.",
                "February 5, 2019",
                "Sci-fi/Thriller",
                "2h 2m",
                7.4,
                R.drawable.poster_alita,
                R.drawable.backdrop_alita,
                null));
        movies.add(new MovieEntity("m3",
                "Cold Pursuit",
                "Nels Coxman’s quiet life as a snowplow driver comes crashing down when his beloved son dies under mysterious circumstances. His search for the truth soon becomes a quest for revenge against a psychotic drug lord named Viking and his sleazy henchmen. Transformed from upstanding citizen to coldblooded vigilante, Coxman unwittingly sets off a chain of events that includes a kidnapping, a series of deadly misunderstandings and a turf war between Viking and a rival boss.",
                "February 15, 2019",
                "Drama/Thriller",
                "1h 38m",
                6.2,
                R.drawable.poster_cold_pursuit,
                R.drawable.backdrop_coldpursuit,
                null));
        movies.add(new MovieEntity("m4",
                "Creed II",
                "In 1985, Russian boxer Ivan Drago killed former U.S. champion Apollo Creed in a tragic match that stunned the world. Against the wishes of trainer Rocky Balboa, Apollo’s son Adonis Johnson accepts a challenge from Drago’s son — another dangerous fighter. Under guidance from Rocky, Adonis trains for the showdown of his life — a date with destiny that soon becomes his obsession. Now, Johnson and Balboa must confront their shared legacy as the past comes back to haunt each man.",
                "November 28, 2018",
                "Drama/Sport",
                "2h 10m",
                7.2,
                R.drawable.poster_creed,
                R.drawable.backdrop_creed2,
                null));
        movies.add(new MovieEntity("m5",
                "How to Train Your Dragon: The Hidden World",
                "From DreamWorks Animation comes a surprising tale about growing up, finding the courage to face the unknown…and how nothing can ever train you to let go. What began as an unlikely friendship between an adolescent Viking and a fearsome Night Fury dragon has become an epic adventure spanning their lives. Welcome to the most astonishing chapter of one of the most beloved animated franchises in film history: How to Train Your Dragon: The Hidden World. Now chief and ruler of Berk alongside Astrid, Hiccup has created a gloriously chaotic dragon utopia. When the sudden appearance of female Light Fury coincides with the darkest threat their village has ever faced, Hiccup and Toothless must leave the only home they’ve known and journey to a hidden world thought only to exist in myth. As their true destines are revealed, dragon and rider will fight together—to the very ends of the Earth—to protect everything they’ve grown to treasure. For How to Train Your Dragon: The Hidden World, series director Dean DeBlois returns alongside the all-star cast. The film is produced by Brad Lewis (Ratatouille, ANTZ) and Bonnie Arnold (Toy Story, How to Train Your Dragon, How to Train Your Dragon 2).",
                "January 9, 2019",
                "Fantasy/Action",
                "1h 44m",
                7.5,
                R.drawable.poster_how_to_train,
                R.drawable.backdrop_traindragon3,
                null));
        movies.add(new MovieEntity("m6",
                "Mary Queen of Scots",
                "Queen of France at 16 and widowed at 18, Mary Stuart defies pressure to remarry. Instead, she returns to her native Scotland to reclaim her rightful throne. However, Scotland and England fall under the rule of the compelling Elizabeth I. Each young Queen beholds her sister in fear and fascination. Rivals in power and in love, and female regents in a masculine world, the two must decide how to play the game of marriage versus independence.",
                "January 9, 2019",
                "Drama/History",
                "2h 5m",
                6.3,
                R.drawable.poster_marry_queen,
                R.drawable.backdrop_maryqueen,
                null));
        movies.add(new MovieEntity("m7",
                "Overlord",
                "On the eve of D-Day, American paratroopers drop behind enemy lines to penetrate the walls of a fortified church and destroy a radio transmitter. As the soldiers approach their target, they soon begin to realize that there’s more going on in the Nazi-occupied village than a simple military operation. Making their way to an underground lab, the outnumbered men stumble upon a sinister experiment that forces them into a vicious battle against an army of the undead.",
                "November 7, 2018",
                "Drama/Thriller",
                "1h 48m",
                6.6,
                R.drawable.poster_overlord,
                R.drawable.backdrop_overlord,
                null));
        movies.add(new MovieEntity("m8",
                "Ralph Breaks the Internet",
                "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope’s video game, \"Sugar Rush.\" In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                "November 23, 2018",
                "Fantasy/Adventure",
                "1h 56m",
                7.1,
                R.drawable.poster_ralph,
                R.drawable.backdrop_ralph2,
                null));
        movies.add(new MovieEntity("m9",
                "Serenity",
                "Baker Dill is a fishing boat captain who leads tours off of the tranquil enclave of Plymouth Island. His peaceful life is soon shattered when his ex-wife Karen tracks him down. Desperate for help, Karen begs Baker to save her — and their young son — from her abusive husband. She wants him to take the brute out for a fishing excursion — then throw him overboard to the sharks. Thrust back into a life that he wanted to forget, Baker now finds himself struggling to choose between right and wrong.",
                "February 1, 2019",
                "Drama/Thriller",
                "1h 46m",
                5.3,
                R.drawable.poster_serenity,
                R.drawable.backdrop_serenity,
                null));
        movies.add(new MovieEntity("m10",
                "T-34",
                "In 1944 a group of Russian soldiers escapes from German captivity in a half-destroyed tank.",
                "December 27, 2018",
                "Drama/Action",
                "2h 19m",
                6.4,
                R.drawable.poster_t34,
                R.drawable.backdrop_t34,
                null));

        return movies;
    }

    public static ArrayList<TvEntity> generateDummyTv() {
        ArrayList<TvEntity> tvs = new ArrayList<>();

        tvs.add(new TvEntity("t1",
                "Game of Thrones",
                "George R.R. Martin’s best-selling book series \"A Song of Ice and Fire\" is brought to the screen as HBO sinks its considerable storytelling teeth into the medieval fantasy epic. It’s the depiction of two powerful families — kings and queens, knights and renegades, liars and honest men — playing a deadly game for control of the Seven Kingdoms of Westeros, and to sit atop the Iron Throne. Martin is credited as a co-executive producer and one of the writers for the series, whose shooting locations include Northern Ireland, Malta, Croatia and Spain.",
                "April 17, 2011 – May 19, 2019",
                "Drama",
                "50–82 minutes",
                9.4,
                R.drawable.poster_got,
                R.drawable.backdrop_got,
                null));
        tvs.add(new TvEntity("t2",
                "Arrow",
                "When presumed-dead billionaire playboy Oliver Queen returns home to Starling City after five years stranded on a remote island in the Pacific, he hides the changes the experience had on him, while secretly seeking reconciliation with his ex, Laurel. By day he picks up where he left off, playing the carefree philanderer he used to be, but at night he dons the alter ego of Arrow and works to right the wrongs of his family and restore the city to its former glory. Complicating his mission is Laurel’s father, Detective Quentin Lance, who is determined to put the vigilante behind bars.",
                "October 10, 2012 – present",
                "Superhero",
                "40–43 minutes",
                7.6,
                R.drawable.poster_arrow,
                R.drawable.backdrop_arrow,
                null));
        tvs.add(new TvEntity("t3",
                "Doom Patrol",
                "Doom Patrol is a team of traumatized and downtrodden superheroes, each of whom has suffered a horrible accident that gave them superhuman abilities but also left them scarred and disfigured. The members of the team have found their purpose through The Chief and have come together to investigate some of the world’s weirdest phenomena. After The Chief mysteriously disappears, though, the reluctant heroes find themselves called to action by Cyborg, who comes to them with a mission that they cannot refuse. Doom Patrol — part support group, part superhero team — is a band of superpowered freaks fighting for a world that wants nothing to do with them.",
                "February 15, 2019 – present",
                "Action/Comedy-drama",
                "45–60 minutes",
                8.1,
                R.drawable.poster_doom_patrol,
                R.drawable.backdrop_doompatrol,
                null));
        tvs.add(new TvEntity("t4",
                "Family Guy",
                "Sick, twisted and politically incorrect, the animated series features the adventures of the Griffin family. Endearingly ignorant Peter and his stay-at-home wife Lois reside in Quahog, R.I., and have three kids. Meg, the eldest child, is a social outcast, and teenage Chris is awkward and clueless when it comes to the opposite sex. The youngest, Stewie, is a genius baby bent on killing his mother and destroying the world. The talking dog, Brian, keeps Stewie in check while sipping martinis and sorting through his own life issues.",
                "January 31, 1999 – present",
                "Animated sitcom",
                "20–23 minutes",
                8.1,
                R.drawable.poster_family_guy,
                R.drawable.backdrop_familyguy,
                null));
        tvs.add(new TvEntity("t5",
                "The Flash",
                "At 11, Barry Allen’s life changed completely when his mother died in a freak accident and his innocent father was convicted of her murder. Orphaned Barry later becomes Detective Joe West. Now a crime-scene investigator, his dedication to learn the truth about his mother’s death drives him to follow up on every new scientific advancement and urban legend. When his latest obsession - a particle accelerator heralded as a world-changing invention - causes an explosion, it creates a freak storm and Barry is struck by lightning. He awakes from a coma nine months later with the power of superspeed. When he learns that others who have gained powers use them for evil, he dedicates himself to protecting the innocent, while still trying to solve the older mystery.",
                "October 7, 2014 – present",
                "Superhero",
                "42–45 minutes",
                7.8,
                R.drawable.poster_flash,
                R.drawable.backdrop_theflash,
                null));
        tvs.add(new TvEntity("t6",
                "Gotham",
                "James Gordon is a rising detective in the dangerously corrupt Gotham City, where his late father was a successful district attorney. Brave, honest and determined to prove himself, Gordon must navigate the dirty politics of Gotham’s justice system as well as the rise of malevolent villains, including The Penguin, The Riddler and Catwoman. The series also chronicles the life of young Bruce Wayne, who becomes an orphan when his billionaire parents Thomas and Martha Wayne are murdered. Gordon becomes a friend to young Bruce as he finds his way toward becoming the iconic caped crusader.",
                "September 22, 2014 – April 25, 2019",
                "Crime Drama",
                "42–49 minutes",
                7.8,
                R.drawable.poster_gotham,
                R.drawable.backdrop_gotham,
                null));
        tvs.add(new TvEntity("t7",
                "Iron Fist",
                "When Danny Rand was 10-years old, he survived a mysterious plane crash that claimed the lives of his extremely wealthy parents. Rescued by warrior monks, Danny grew up in the of city of K’un-Lun, where he endured harsh conditions, but also trained to be a fierce warrior. Years later, Danny returns home to New York, where he wants to reconnect with his past and take his rightful place at his family’s company, which is being run by his father’s former business partner. Danny hopes to restore his family legacy by defeating the people who threaten it.",
                "March 17, 2017 – September 7, 2018",
                "Superhero",
                "49–61 minutes",
                6.5,
                R.drawable.poster_iron_fist,
                R.drawable.backdrop_ironfist,
                null));
        tvs.add(new TvEntity("t8",
                "Supergirl",
                "At 12 years old, Kara Zor-El escapes doom on planet Krypton to find protection on Earth with the Danver family, where she grows up in the shadow of her foster sister, Alex, and learns to hide the extraordinary powers she shares with her cousin, Superman. Now an adult living in National City and working for media mogul Cat Grant, Kara finds her days of keeping her abilities a secret are over when super-secret agency head Hank Henshaw enlists her to help protect the city’s citizens from threats. Finally coming into her own, Kara must juggle her new responsibilities with her very human relationships.",
                "October 26, 2015 – present",
                "Superhero",
                "45 minutes",
                6.3,
                R.drawable.poster_supergirl,
                R.drawable.backdrop_supergirl,
                null));
        tvs.add(new TvEntity("t9",
                "The Simpsons",
                "This long-running animated comedy focuses on the eponymous family in the town of Springfield in an unnamed U.S. state. The head of the Simpson family, Homer, is not a typical family man. A nuclear-plant employee, he does his best to lead his family but often finds that they are leading him. The family includes loving, blue-haired matriarch Marge, troublemaking son Bart, overachieving daughter Lisa and baby Maggie. Other Springfield residents include the family’s religious neighbour, Ned Flanders, family physician Dr Hibbert, Moe the bartender and police chief Clancy Wiggum.",
                "December 17, 1989 – present",
                "Animated sitcom",
                "21–24 minutes",
                8.7,
                R.drawable.poster_the_simpson,
                R.drawable.backdrop_thesimpsons,
                null));
        tvs.add(new TvEntity("t10",
                "The Walking Dead",
                "Based on the comic book series written by Robert Kirkman, this gritty drama portrays life in the months and years that follow a zombie apocalypse. Led by former police officer Rick Grimes, his family and a group of other survivors find themselves constantly on the move in search of a safe and secure home. But the pressure each day to stay alive sends many in the group to the deepest depths of human cruelty, and they soon discover that the overwhelming fear of the survivors can be more deadly than the zombies walking among them.",
                "October 31, 2010 – present",
                "Post-apocalyptic horror",
                "41–67 minutes",
                8.3,
                R.drawable.poster_the_walking_dead,
                R.drawable.backdrop_thewalkingdead,
                null));

        return tvs;
    }

    public static ArrayList<FilmResponse> generateRemoteDummyMovie() {
        ArrayList<FilmResponse> movies = new ArrayList<>();

        movies.add(new FilmResponse("m1",
                "Fantastic Beasts: The Crimes of Grindelwald",
                "The second installment of the \"Fantastic Beasts\" series featuring the adventures of Magizoologist Newt Scamander.",
                "November 14, 2018",
                "Drama/Fantasy",
                "2h 13m",
                6.6,
                R.drawable.poster_crimes,
                R.drawable.backdrop_fbeast));
        movies.add(new FilmResponse("m2",
                "Alita: Battle Angel",
                "Set several centuries in the future, the abandoned Alita is found in the scrapyard of Iron City by Ido, a compassionate cyber-doctor who takes the unconscious cyborg Alita to his clinic. When Alita awakens, she has no memory of who she is, nor does she have any recognition of the world she finds herself in. As Alita learns to navigate her new life and the treacherous streets of Iron City, Ido tries to shield her from her mysterious past.",
                "February 5, 2019",
                "Sci-fi/Thriller",
                "2h 2m",
                7.4,
                R.drawable.poster_alita,
                R.drawable.backdrop_alita));
        movies.add(new FilmResponse("m3",
                "Cold Pursuit",
                "Nels Coxman’s quiet life as a snowplow driver comes crashing down when his beloved son dies under mysterious circumstances. His search for the truth soon becomes a quest for revenge against a psychotic drug lord named Viking and his sleazy henchmen. Transformed from upstanding citizen to coldblooded vigilante, Coxman unwittingly sets off a chain of events that includes a kidnapping, a series of deadly misunderstandings and a turf war between Viking and a rival boss.",
                "February 15, 2019",
                "Drama/Thriller",
                "1h 38m",
                6.2,
                R.drawable.poster_cold_pursuit,
                R.drawable.backdrop_coldpursuit));
        movies.add(new FilmResponse("m4",
                "Creed II",
                "In 1985, Russian boxer Ivan Drago killed former U.S. champion Apollo Creed in a tragic match that stunned the world. Against the wishes of trainer Rocky Balboa, Apollo’s son Adonis Johnson accepts a challenge from Drago’s son — another dangerous fighter. Under guidance from Rocky, Adonis trains for the showdown of his life — a date with destiny that soon becomes his obsession. Now, Johnson and Balboa must confront their shared legacy as the past comes back to haunt each man.",
                "November 28, 2018",
                "Drama/Sport",
                "2h 10m",
                7.2,
                R.drawable.poster_creed,
                R.drawable.backdrop_creed2));
        movies.add(new FilmResponse("m5",
                "How to Train Your Dragon: The Hidden World",
                "From DreamWorks Animation comes a surprising tale about growing up, finding the courage to face the unknown…and how nothing can ever train you to let go. What began as an unlikely friendship between an adolescent Viking and a fearsome Night Fury dragon has become an epic adventure spanning their lives. Welcome to the most astonishing chapter of one of the most beloved animated franchises in film history: How to Train Your Dragon: The Hidden World. Now chief and ruler of Berk alongside Astrid, Hiccup has created a gloriously chaotic dragon utopia. When the sudden appearance of female Light Fury coincides with the darkest threat their village has ever faced, Hiccup and Toothless must leave the only home they’ve known and journey to a hidden world thought only to exist in myth. As their true destines are revealed, dragon and rider will fight together—to the very ends of the Earth—to protect everything they’ve grown to treasure. For How to Train Your Dragon: The Hidden World, series director Dean DeBlois returns alongside the all-star cast. The film is produced by Brad Lewis (Ratatouille, ANTZ) and Bonnie Arnold (Toy Story, How to Train Your Dragon, How to Train Your Dragon 2).",
                "January 9, 2019",
                "Fantasy/Action",
                "1h 44m",
                7.5,
                R.drawable.poster_how_to_train,
                R.drawable.backdrop_traindragon3));
        movies.add(new FilmResponse("m6",
                "Mary Queen of Scots",
                "Queen of France at 16 and widowed at 18, Mary Stuart defies pressure to remarry. Instead, she returns to her native Scotland to reclaim her rightful throne. However, Scotland and England fall under the rule of the compelling Elizabeth I. Each young Queen beholds her sister in fear and fascination. Rivals in power and in love, and female regents in a masculine world, the two must decide how to play the game of marriage versus independence.",
                "January 9, 2019",
                "Drama/History",
                "2h 5m",
                6.3,
                R.drawable.poster_marry_queen,
                R.drawable.backdrop_maryqueen));
        movies.add(new FilmResponse("m7",
                "Overlord",
                "On the eve of D-Day, American paratroopers drop behind enemy lines to penetrate the walls of a fortified church and destroy a radio transmitter. As the soldiers approach their target, they soon begin to realize that there’s more going on in the Nazi-occupied village than a simple military operation. Making their way to an underground lab, the outnumbered men stumble upon a sinister experiment that forces them into a vicious battle against an army of the undead.",
                "November 7, 2018",
                "Drama/Thriller",
                "1h 48m",
                6.6,
                R.drawable.poster_overlord,
                R.drawable.backdrop_overlord));
        movies.add(new FilmResponse("m8",
                "Ralph Breaks the Internet",
                "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope’s video game, \"Sugar Rush.\" In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                "November 23, 2018",
                "Fantasy/Adventure",
                "1h 56m",
                7.1,
                R.drawable.poster_ralph,
                R.drawable.backdrop_ralph2));
        movies.add(new FilmResponse("m9",
                "Serenity",
                "Baker Dill is a fishing boat captain who leads tours off of the tranquil enclave of Plymouth Island. His peaceful life is soon shattered when his ex-wife Karen tracks him down. Desperate for help, Karen begs Baker to save her — and their young son — from her abusive husband. She wants him to take the brute out for a fishing excursion — then throw him overboard to the sharks. Thrust back into a life that he wanted to forget, Baker now finds himself struggling to choose between right and wrong.",
                "February 1, 2019",
                "Drama/Thriller",
                "1h 46m",
                5.3,
                R.drawable.poster_serenity,
                R.drawable.backdrop_serenity));
        movies.add(new FilmResponse("m10",
                "T-34",
                "In 1944 a group of Russian soldiers escapes from German captivity in a half-destroyed tank.",
                "December 27, 2018",
                "Drama/Action",
                "2h 19m",
                6.4,
                R.drawable.poster_t34,
                R.drawable.backdrop_t34));

        return movies;
    }

    public static ArrayList<FilmResponse> generateRemoteDummyTv() {
        ArrayList<FilmResponse> tvs = new ArrayList<>();

        tvs.add(new FilmResponse("t1",
                "Game of Thrones",
                "George R.R. Martin’s best-selling book series \"A Song of Ice and Fire\" is brought to the screen as HBO sinks its considerable storytelling teeth into the medieval fantasy epic. It’s the depiction of two powerful families — kings and queens, knights and renegades, liars and honest men — playing a deadly game for control of the Seven Kingdoms of Westeros, and to sit atop the Iron Throne. Martin is credited as a co-executive producer and one of the writers for the series, whose shooting locations include Northern Ireland, Malta, Croatia and Spain.",
                "April 17, 2011 – May 19, 2019",
                "Drama",
                "50–82 minutes",
                9.4,
                R.drawable.poster_got,
                R.drawable.backdrop_got));
        tvs.add(new FilmResponse("t2",
                "Arrow",
                "When presumed-dead billionaire playboy Oliver Queen returns home to Starling City after five years stranded on a remote island in the Pacific, he hides the changes the experience had on him, while secretly seeking reconciliation with his ex, Laurel. By day he picks up where he left off, playing the carefree philanderer he used to be, but at night he dons the alter ego of Arrow and works to right the wrongs of his family and restore the city to its former glory. Complicating his mission is Laurel’s father, Detective Quentin Lance, who is determined to put the vigilante behind bars.",
                "October 10, 2012 – present",
                "Superhero",
                "40–43 minutes",
                7.6,
                R.drawable.poster_arrow,
                R.drawable.backdrop_arrow));
        tvs.add(new FilmResponse("t3",
                "Doom Patrol",
                "Doom Patrol is a team of traumatized and downtrodden superheroes, each of whom has suffered a horrible accident that gave them superhuman abilities but also left them scarred and disfigured. The members of the team have found their purpose through The Chief and have come together to investigate some of the world’s weirdest phenomena. After The Chief mysteriously disappears, though, the reluctant heroes find themselves called to action by Cyborg, who comes to them with a mission that they cannot refuse. Doom Patrol — part support group, part superhero team — is a band of superpowered freaks fighting for a world that wants nothing to do with them.",
                "February 15, 2019 – present",
                "Action/Comedy-drama",
                "45–60 minutes",
                8.1,
                R.drawable.poster_doom_patrol,
                R.drawable.backdrop_doompatrol));
        tvs.add(new FilmResponse("t4",
                "Family Guy",
                "Sick, twisted and politically incorrect, the animated series features the adventures of the Griffin family. Endearingly ignorant Peter and his stay-at-home wife Lois reside in Quahog, R.I., and have three kids. Meg, the eldest child, is a social outcast, and teenage Chris is awkward and clueless when it comes to the opposite sex. The youngest, Stewie, is a genius baby bent on killing his mother and destroying the world. The talking dog, Brian, keeps Stewie in check while sipping martinis and sorting through his own life issues.",
                "January 31, 1999 – present",
                "Animated sitcom",
                "20–23 minutes",
                8.1,
                R.drawable.poster_family_guy,
                R.drawable.backdrop_familyguy));
        tvs.add(new FilmResponse("t5",
                "The Flash",
                "At 11, Barry Allen’s life changed completely when his mother died in a freak accident and his innocent father was convicted of her murder. Orphaned Barry later becomes Detective Joe West. Now a crime-scene investigator, his dedication to learn the truth about his mother’s death drives him to follow up on every new scientific advancement and urban legend. When his latest obsession - a particle accelerator heralded as a world-changing invention - causes an explosion, it creates a freak storm and Barry is struck by lightning. He awakes from a coma nine months later with the power of superspeed. When he learns that others who have gained powers use them for evil, he dedicates himself to protecting the innocent, while still trying to solve the older mystery.",
                "October 7, 2014 – present",
                "Superhero",
                "42–45 minutes",
                7.8,
                R.drawable.poster_flash,
                R.drawable.backdrop_theflash));
        tvs.add(new FilmResponse("t6",
                "Gotham",
                "James Gordon is a rising detective in the dangerously corrupt Gotham City, where his late father was a successful district attorney. Brave, honest and determined to prove himself, Gordon must navigate the dirty politics of Gotham’s justice system as well as the rise of malevolent villains, including The Penguin, The Riddler and Catwoman. The series also chronicles the life of young Bruce Wayne, who becomes an orphan when his billionaire parents Thomas and Martha Wayne are murdered. Gordon becomes a friend to young Bruce as he finds his way toward becoming the iconic caped crusader.",
                "September 22, 2014 – April 25, 2019",
                "Crime Drama",
                "42–49 minutes",
                7.8,
                R.drawable.poster_gotham,
                R.drawable.backdrop_gotham));
        tvs.add(new FilmResponse("t7",
                "Iron Fist",
                "When Danny Rand was 10-years old, he survived a mysterious plane crash that claimed the lives of his extremely wealthy parents. Rescued by warrior monks, Danny grew up in the of city of K’un-Lun, where he endured harsh conditions, but also trained to be a fierce warrior. Years later, Danny returns home to New York, where he wants to reconnect with his past and take his rightful place at his family’s company, which is being run by his father’s former business partner. Danny hopes to restore his family legacy by defeating the people who threaten it.",
                "March 17, 2017 – September 7, 2018",
                "Superhero",
                "49–61 minutes",
                6.5,
                R.drawable.poster_iron_fist,
                R.drawable.backdrop_ironfist));
        tvs.add(new FilmResponse("t8",
                "Supergirl",
                "At 12 years old, Kara Zor-El escapes doom on planet Krypton to find protection on Earth with the Danver family, where she grows up in the shadow of her foster sister, Alex, and learns to hide the extraordinary powers she shares with her cousin, Superman. Now an adult living in National City and working for media mogul Cat Grant, Kara finds her days of keeping her abilities a secret are over when super-secret agency head Hank Henshaw enlists her to help protect the city’s citizens from threats. Finally coming into her own, Kara must juggle her new responsibilities with her very human relationships.",
                "October 26, 2015 – present",
                "Superhero",
                "45 minutes",
                6.3,
                R.drawable.poster_supergirl,
                R.drawable.backdrop_supergirl));
        tvs.add(new FilmResponse("t9",
                "The Simpsons",
                "This long-running animated comedy focuses on the eponymous family in the town of Springfield in an unnamed U.S. state. The head of the Simpson family, Homer, is not a typical family man. A nuclear-plant employee, he does his best to lead his family but often finds that they are leading him. The family includes loving, blue-haired matriarch Marge, troublemaking son Bart, overachieving daughter Lisa and baby Maggie. Other Springfield residents include the family’s religious neighbour, Ned Flanders, family physician Dr Hibbert, Moe the bartender and police chief Clancy Wiggum.",
                "December 17, 1989 – present",
                "Animated sitcom",
                "21–24 minutes",
                8.7,
                R.drawable.poster_the_simpson,
                R.drawable.backdrop_thesimpsons));
        tvs.add(new FilmResponse("t10",
                "The Walking Dead",
                "Based on the comic book series written by Robert Kirkman, this gritty drama portrays life in the months and years that follow a zombie apocalypse. Led by former police officer Rick Grimes, his family and a group of other survivors find themselves constantly on the move in search of a safe and secure home. But the pressure each day to stay alive sends many in the group to the deepest depths of human cruelty, and they soon discover that the overwhelming fear of the survivors can be more deadly than the zombies walking among them.",
                "October 31, 2010 – present",
                "Post-apocalyptic horror",
                "41–67 minutes",
                8.3,
                R.drawable.poster_the_walking_dead,
                R.drawable.backdrop_thewalkingdead));

        return tvs;
    }

    public static MovieEntity generateDummyDetailMovie(MovieEntity movie, boolean favorited) {
        movie.setFavorited(favorited);
        return movie;
    }

    public static TvEntity generateDummyDetailTv(TvEntity tv, boolean favorited) {
        tv.setFavorited(favorited);
        return tv;
    }
}
