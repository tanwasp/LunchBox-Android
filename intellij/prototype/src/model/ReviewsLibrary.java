package model;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;

public class ReviewsLibrary {
    private HashMap<String, Review> data;

    public ReviewsLibrary(){
        this.data = new HashMap<String, Review>();
        this.loadReviews();
    }

    public String addReview(User curUser, String restaurantId, float rating, String reviewText){
        String reviewId = "review" + (this.data.size() + 100);
        Review newReview = new Review(reviewId, curUser.username, restaurantId, rating, reviewText);
        this.data.put(reviewId, newReview);
        return reviewId;
    }

    public String displayReviews(ArrayList<String> reviews){
        StringBuilder output = new StringBuilder();
        if (reviews.isEmpty()) {
            return "No reviews available yet.";
        }
        output.append("=====================================\n");
        output.append("           REVIEWS               \n");
        output.append("=====================================\n");
        for (String reviewId : reviews) {
            Review review = this.data.get(reviewId);
            if (review == null){
                continue;
            }
            output.append("Username: ").append(review.username).append("\n");
            output.append("Rating: ").append(review.rating).append("\n");
            output.append("Review: ").append(review.body).append("\n");
            output.append("-------------------------------------\n");
        }
        System.out.println(output.toString());  
        return output.toString();
    }

    private void loadReviews() {

        Review rev1 = new Review("review1", "burchdanielle", "restaurant6", 2.5f, "A me try writer off enough. Road hope wall onto foot. Better require until peace. Half official always why who body take. That rate region over task.");
        this.data.put("review1", rev1);

        Review rev4 = new Review("review4", "haleamy", "restaurant8", 3.0f, "Best care when certainly. Actually simple able against floor PM improve collection. Number place contain pressure believe play.");
        this.data.put("review4", rev4);

        Review rev5 = new Review("review5", "katherinegreen", "restaurant9", 2.5f, "Type despite close hit few. Throughout happen consumer score half serve why. No now couple market at wide. None lose year order forget.");
        this.data.put("review5", rev5);

        Review rev6 = new Review("review6", "lthomas", "restaurant10", 1.5f, "Change dark lay free brother section even. Program cold beautiful miss situation morning live. Benefit white value of most ask. Care week record father best senior.");
        this.data.put("review6", rev6);

        Review rev7 = new Review("review7", "brian78", "restaurant3", 5.0f, "Should room imagine country watch. Measure activity certain hit want happy. Wish concern there six wonder others accept.");
        this.data.put("review7", rev7);

        Review rev8 = new Review("review8", "suttonronald", "restaurant11", 1.0f, "Night miss however back bring spring. Call seem amount federal body right. Give third treat laugh.");
        this.data.put("review8", rev8);

        Review rev9 = new Review("review9", "ryanjackson", "restaurant12", 5.0f, "Enough matter whom far. Teacher safe push try little. Human open language. Live nothing cause really if. Give treat civil. I research director west.");
        this.data.put("review9", rev9);

        Review rev10 = new Review("review10", "dduncan", "restaurant13", 5.0f, "Store note audience myself. Indicate population pay foot must. Over fire play keep scene ever. Whose alone gas difficult central room.");
        this.data.put("review10", rev10);

        Review rev13 = new Review("review13", "heidi89", "restaurant14", 4.5f, "Especially expect help trouble election. Point action detail lead tonight born left. Decision sort trial wall hear make month. Only own stock success wait. Here style see perform.");
        this.data.put("review13", rev13);

        Review rev15 = new Review("review15", "kimberly99", "restaurant16", 4.0f, "It hospital suddenly public somebody her impact. Lay group rise laugh ahead main. Debate together there however. At happen shoulder rate offer let a. Inside west exactly stop buy day.");
        this.data.put("review15", rev15);

        Review rev18 = new Review("review18", "nperez", "restaurant19", 4.5f, "Provide series tend stuff size meeting. Three most paper. Doctor state center officer condition. Health plant surface media.");
        this.data.put("review18", rev18);

        Review rev20 = new Review("review20", "vbennett", "restaurant5", 1.5f, "Population weight company thank assume loss I. Practice decade left memory. Per structure get prevent store. ank east arm store. White nation or police. Where information process pull long.");
        this.data.put("review20", rev20);

        Review rev21 = new Review("review21", "ecooper", "restaurant1", 3.5f, "Picture alone accept author politics. Look standard myself change travel success sound. Benefit prevent face woman arm answer reveal detail.");
        this.data.put("review21", rev21);

        Review rev22 = new Review("review22", "johnnyvalencia", "restaurant7", 1.0f, "Pull interest rather finish doctor different executive. Real bed series dog message describe. Sing attorney the military knowledge happy hard. Direction expert happy represent central window.");
        this.data.put("review22", rev22);

        Review rev23 = new Review("review23", "tamarashort", "restaurant4", 1.5f, "Mean beat laugh allow forward. Shake pretty level group difficult heart figure happen. Letter present fight tax free visit. Speech although machine truth.");
        this.data.put("review23", rev23);

        Review rev24 = new Review("review24", "dhill", "restaurant5", 1.0f, "Similar bar story. Join hot dog paper note news. I kitchen find toward. Simple west example difference risk machine of. Drop attorney guess national husband ahead wind.");
        this.data.put("review24", rev24);

        Review rev29 = new Review("review29", "nathan55", "restaurant5", 5.0f, "Hear decade international interest these. Almost dream experience future. Suddenly fill less some partner.");
        this.data.put("review29", rev29);

        Review rev30 = new Review("review30", "ymcmahon", "restaurant2", 1.5f, "White arrive little off available. Would easy agency full garden. Low stand free sister than which finally.");
        this.data.put("review30", rev30);

        Review rev31 = new Review("review31", "levinejason", "restaurant20", 1.5f, "Kitchen big describe animal. Particular team candidate part. Effort week real machine. His matter hour. Themselves must leader near. Give piece trial wonder air they.");
        this.data.put("review31", rev31);

        Review rev32 = new Review("review32", "vcarter", "restaurant2", 3.5f, "Every example prevent still himself light page. Require agreement apply business factor road bit. Hear girl another southern smile one picture fine. School between us forget. Lawyer behind type cold.");
        this.data.put("review32", rev32);

        Review rev33 = new Review("review33", "claudiaryan", "restaurant5", 3.0f, "Provide learn seem own. Trip loss whatever experience. Live major available space discover use citizen summer. Question site police method. Church measure bring there. Onto person not.");
        this.data.put("review33", rev33);

        Review rev34 = new Review("review34", "sierra93", "restaurant11", 1.5f, "Already kid audience down. Reduce price attack own trip half. Stand among difficult appear list.");
        this.data.put("review34", rev34);

        Review rev35 = new Review("review35", "tammy59", "restaurant3", 2.5f, "Special plant own organization special live. Nature population we animal stand happen nation.");
        this.data.put("review35", rev35);

        Review rev36 = new Review("review36", "michaeldurham", "restaurant16", 3.0f, "Include former Mr ever hour energy know. Network could example long. Artist art myself myself authority current along.");
        this.data.put("review36", rev36);

        Review rev37 = new Review("review37", "michael87", "restaurant1", 5.0f, "Set quickly security phone seek. Rate recent PM back season much. Expect society within ability. Tax style evening several player institution. Political near game thus save fine seek.");
        this.data.put("review37", rev37);

        Review rev38 = new Review("review38", "mcguirehannah", "restaurant8", 4.5f, "Begin executive customer become. Trade culture shake. Participant should traditional leg nothing. Owner identify continue west wife member piece. Arrive first east live respond.");
        this.data.put("review38", rev38);

        Review rev39 = new Review("review39", "nicole82", "restaurant8", 3.0f, "Small eat reduce and husband your project. Field time thing collection seem system morning. Memory among dark allow upon worker city. Brother opportunity respond activity store its.");
        this.data.put("review39", rev39);

        Review rev40 = new Review("review40", "fieldsrobert", "restaurant3", 5.0f, "Watch future second young job. Let bed pattern high perform while. Data play cold job human trade. Appear them up decide she. May food receive.");
        this.data.put("review40", rev40);

        Review rev42 = new Review("review42", "melissa99", "restaurant18", 4.5f, "Single position risk economic likely result pattern. Need cultural property. Spring discussion yet left. Create choose spring party leave magazine shoulder. There goal student power ability security.");
        this.data.put("review42", rev42);

        Review rev43 = new Review("review43", "jennifer28", "restaurant13", 4.0f, "Rich property company action around national. Laugh economic until peace. Team computer than next represent about. Animal away recent. Son future ahead speech yet until.");
        this.data.put("review43", rev43);

        Review rev44 = new Review("review44", "webberic", "restaurant10", 2.5f, "School conference side possible. Store break stand close. Education industry question cup. Somebody door investment case once rest. General everything interview save mouth protect stand.");
        this.data.put("review44", rev44);

        Review rev45 = new Review("review45", "perrycourtney", "restaurant17", 2.5f, "Manager city kind understand today fill large. Without price research vote require most. Voice relate school fill process pattern. Everything evidence play national.");
        this.data.put("review45", rev45);

        Review rev47 = new Review("review47", "linda99", "restaurant15", 3.0f, "Region compare arm. Be land to similar alone stop. Person born hand low. Development each since skin anyone hit. Call focus establish capital series sign mean skin. Great no employee.");
        this.data.put("review47", rev47);

        Review rev48 = new Review("review48", "reevesstephanie", "restaurant12", 2.0f, "Deal debate avoid government executive face. Question many day can clear college. About condition nature.");
        this.data.put("review48", rev48);

        Review rev50 = new Review("review50", "omartinez", "restaurant10", 1.0f, "Foot accept nothing class idea happen. Model perform poor air prove. People good whose produce.");
        this.data.put("review50", rev50);

        Review rev51 = new Review("review51", "melindaguerra", "restaurant20", 2.0f, "Save carry imagine. Another bring evidence collection.");
        this.data.put("review51", rev51);

        Review rev52 = new Review("review52", "donaldcampbell", "restaurant2", 3.0f, "Particular perform choose story suffer full popular why. Film by whatever realize food. Boy live tree field model than teacher. Moment might eye wait economy. Style if truth enter gas sign past.");
        this.data.put("review52", rev52);

        Review rev54 = new Review("review54", "xmontgomery", "restaurant14", 4.5f, "Mother American this development military worry. Page quality write instead middle game. Grow individual left reality. Involve include what beyond staff lot new. Growth east notice hit candidate.");
        this.data.put("review54", rev54);

        Review rev55 = new Review("review55", "william60", "restaurant6", 5.0f, "Table director kind sea week. Color where great skill life win. Source reach real season million interesting might. Form game certainly authority could radio.");
        this.data.put("review55", rev55);

        Review rev57 = new Review("review57", "cwhitehead", "restaurant19", 5.0f, "Value also magazine race water. Reveal design mother truth subject by. Sea production win ball tend.");
        this.data.put("review57", rev57);

        Review rev58 = new Review("review58", "cheryl70", "restaurant2", 4.0f, "Pattern line seven involve probably go suggest. Fish parent reveal name film chair. Response step seem shake service. Professor especially want position. Single rather group factor happen.");
        this.data.put("review58", rev58);

        Review rev60 = new Review("review60", "fburgess", "restaurant15", 4.5f, "Science model exist everyone people they. She factor late stock itself blood he must. Show quality operation also social. Among recently evening physical tree strong.");
        this.data.put("review60", rev60);

        Review rev61 = new Review("review61", "mgriffith", "restaurant13", 2.5f, "Weight her him foot material do president. Star PM pattern report interview. Address like continue.");
        this.data.put("review61", rev61);

        Review rev62 = new Review("review62", "mendozaandrew", "restaurant1", 1.0f, "Meeting establish happen together then heavy teach enjoy. One music safe value none. Father society great. Remain think treatment. Various lay stand edge I. Though tree word fish piece left.");
        this.data.put("review62", rev62);

        Review rev64 = new Review("review64", "qhudson", "restaurant1", 3.5f, "Example carry end Mr. Boy tree indicate society history tell stock. Approach citizen modern.");
        this.data.put("review64", rev64);

        Review rev65 = new Review("review65", "qturner", "restaurant14", 4.0f, "Meet design military explain audience. Former near space respond. Me author record else go material cut. So too ready base listen bad hard beautiful. Should increase member through explain fish.");
        this.data.put("review65", rev65);

        Review rev67 = new Review("review67", "gibarra", "restaurant16", 3.5f, "Type industry reveal billion. North keep set skin. Girl animal hear television. Shake check story company. Page street land first attention certain soon. Per popular your.");
        this.data.put("review67", rev67);
    }



}
