package Contest.NineChapter.HighFreq.String;

import java.util.HashSet;
import java.util.Set;

public class RepeatSubstringPattern {

    public static void main(String[] args) {
        RepeatSubstringPattern sol = new RepeatSubstringPattern();
        boolean res = sol.repeatedSubstringPattern("nwlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfw" +
                "khopkmcoqhnwnkuewhsqmgbbuqcljjivswmdkqtbxixmvtrrbljptnsnfwzqfjmafadrrwsofsbcnuvqhffbsaqxwpqcacehchzvfrkmlnozjk" +
                "pqpxrjxkitzyxacbhhkicqcoendtomfgdwdwfcgpxiqvkuytdlcgdewhtaciohordtqkvwcsgspqoqmsboaguwnnyqxnzlgdgwpbtrwblnsadeugu" +
                "umoqcdrubetokyxhoachwdvmxxrdryxlmndqtukwagmlejuukwcibxubumenmeyatdrmydiajxloghiqfmzhlvihjouvsuyoypayulyeimuotehzriicfsk" +
                "pggkbbipzzrzucxamludfykgruowzgiooobppleqlwphapjnadqhdcnvwdtxjbmyppphauxnspusgdhiixqmbfjxjcvudjsuyibyebmwsiqyoygyxymzevypzv" +
                "jegebeocfuftsxdixtigsieehkchzdflilrjqfnxztqrsvbspkyhsenbppkqtpddbuotbbqcwivrfxjujjddntgeiqvdgaijvwcyaubwewpjvygehljxepbpiw" +
                "uqzdzubdubzvafspqpqwuzifwovyddwyvvburczmgyjgfdxvtnunneslsplwuiupfxlzbknhkwppanltcfirjcddsozoyvegurfwcsfmoxeqmrjowrghwlkobmeahk" +
                "gccnaehhsveymqpxhlrnunyfdzrhbasjeuygafoubutpnimuwfjqsjxvkqdorxxvrwctdsneogvbpkxlpgdirbfcriqifpgynkrrefxsnvucftpwctgtwmxnupycf" +
                "gcuqunublmoiitncklefszbexrampetvhqnddjeqvuygpnkazqfrpjvoaxdpcwmjobmskskfojnewxgxnnofwltwjwnnvbwjckdmeouuzhyvhgvwujbqxxp" +
                "itcvograiddvhrrdsycqhkleewhxtembaqwqwpqhsuebnvfgvjwdvjjafqzzxlcxdzncqgjlapopkvxfgvicetcmkbljopgtqvvhbgsdvivhesnkqxmwrqi" +
                "drvmhlubbryktheyentmrobdeyqcrgluaiihveixwjjrqopubjguxhxdipfzwswybgfylqvjzharvrlyauuzdrcnjkphclffrkeecbpdipufhidjcxj" +
                "hrnxcxmjcxohqanxdrmgzebhnlmwpmhwdvthsfqueeexgrujigskmvrzgfwvrftwapdtutpbztygnsrxajjngcomikjzsdwssznovdruypcnjulkfuzmxn" +
                "afamespckjcazxdrtdgyrqscczybnvqqcqcjitlvcnvbmasidzgwraatzzwpwmfbfjkncvkelhhzjchpdnlunmppnlgjznkewwuysgefonexpmmsbaopmdgzqm" +
                "kqzxuvtqvnxbslqzkglzamzpdnsjolvybwxxttqognrbaiakqllszkhfzconnmoqklpeefsnsmouwqhodsgcfohesyshmgxwtoayuvnojdjftqtwkbapri" +
                "ujimqwspslgvlcsaqbdbgwtbseettwdnfnbyjvpdjxyuzqxstatbzpctthoofremgfkrbcvkzvgbofthgojhdnaywpnbitoraaibednezwfpdawlohssvtqtkfv" +
                "syljzlucqxswyqdntdmfrtzlqsekejhzksklfepxchvczysvdgcxbbiswmeaylzifktmoikssfxtgpojxqiysrsqfwqdjqnqcgdqrnlluieazvmwnuufnnxv" +
                "loyvgmliuqandlyavfauaosnlnvacsvpiumoiawcqxswkqwgxyazntnaikameybnuqbcqaggxachrynqxqqmlfotpqhvokiiammqmvxjvbsoaifzyxnjcberrn" +
                "mixxsyjhovengbpyqrixqgwdrygxrxkfhicainhwilkqmbpeszdigznzxtzqsjwatycbmjawwmninepfduplucltxmkpvgrrgtuseurageltkcapwpbqromqa" +
                "wixezqkvlfbhwcocpjmrmbpbegvsuluqtuuvkesvjtdhvtjmexfqbvufdpaxcwnwqjtbplyzedicwsodpwtqrpyuearhwgfnpaqelofrsotqiktxipqzeqvlqmuo" +
                "ubbjbrpmixfclbstnosvdkujcpwsdqhxrkiueziowoqjpiecwxxbjtnmkjgncpmvauqgtausokbfugjtfiuqbjclvlazamucimicnewdoxjlfuemdadgkhuf" +
                "suevjaxrnivcorhfrqqwnujquoyevslqprlyskrhunljgsoxleuyyfqutozqhmgyetyyepfaesjlkzivdevdllygazxjndjrxhrdyyddqnqdoayshwxs" +
                "hxzjywumbffamxdnxjqoyirmirernekxdlicjfqkkvnxuqmszcixmzkwsqoiwyfalpeuuugfrteomqinuqnirxelpstosaodqszkogrfbxtnpdblt" +
                "qtmpyyeqtujuiokcowswqyxntndxqqsgkhvihbaawjugagloddktdjizynyoesuozryityjrifximkyrokktvusuiqiojfckyatryekijks" +
                "vusokcyeavwufpctajxkixdbxjmitwcqqxfbbfhbadvfuaaujxfrwkvuuhepdifvfkyhsfiuleafgaapahjwtesplweqfmnmymtqreleinkopmfpvom" +
                "queghdmxkynwxzqnswaxgnjwdxbuusgkmnqwqdvadiwahoqakqzqgkmlhqfdimnwzgsplorownpgehioxhhfrvqalwdtksslykajataxgpdmyldxukdnftprr" +
                "umbmemlrowrhwoqntclghlcrorzhgsbaecplpccdyvnxmdmfhaoplqizkhiqbjtimitdkxiksxjecwmkwabhslievqvwcqeqaztkydwrbgxdcj" +
                "palshgepkzhhvlxcbxdwjccgtdoqiscyspqzvuqivzptlpvooynyapgvswoaosaghrffnxnjyeeltzaizniccozwknwyhzgpqlwfkjqipuujvwtxlb" +
                "znryjdohbvghmyuiggtyqjtmuqinntqmihntkddnalwnmsxsatqqeldacnnpjfermrnyuqnwbjjpdjhdeavknykpoxhxclqqedqavdwzoiorrwwxyrhlsr" +
                "dgqkduvtmzzczufvtvfioygkvedervvudneghbctcbxdxezrzgbpfhzanffeccbgqfmzjqtlrsppxqiywjobspefujlxnmddurddiyobqfspvcoulcvdrzkmkw" +
                "lyiqdchghrgytzdnobqcvdeqjystmepxcaniewqmoxkjwpymqorluxedvywhcoghotpusfgiestckrpaigocfufbubiyrrffmwaeeimidfnnzcphkflpbq" +
                "svtdwludsgaungfzoihbxifoprwcjzsdxngtacwtjypweuxvegybogsfxqhipbompufpxckicaghufczmaccgwigmrqcteqkbwbaamicoqlivnjjoomwkuczn" +
                "vdgztqarsargkwuaheyvohletjqpopdjslkoelfynzeavaaceazuimydypvmgyxblhppuunkttkqtmvanuuvjvahmvvuvsvhzkywhmgchqvdcqdpmzmxw" +
                "neikzfgtantnlpwzvahnvkplpfaotxngexrfczzdmuszlobiokvkwkxlrxblvotzomeqlftxzlzkbcsqmnciazvrzyeupyvdkbtwhpvgcwteylwkbyubxruws" +
                "zshxpmjrhfawdibzbfypdksbhtaapzsorbnjpzcxecvjmwjqdjhgvzjcukfjjzacbpnsoppvtleijpynyfvuefyyrdjadjegbsypjomfbrnkilzhsvbwczwtdfvirbo" +
                "svmjfcymdrzqzkpgemjaojyjofeywimqdackdawitxysjqzncipttncjtjhrtvkwwojbqhjjfkboaccenrxihcsanbtgxdcttnujvfscrwqtyuynmxwvbqx" +
                "orquowzhpmdzjlrlcncyoywbmvzhxpenhvivthfivkhfxbqaquyetwifthnsxrggoqbhxiqsvzzscqutmszfqjnmtaeqtmykcbrzkjuhltznluiyokfhvstouzgqme" +
                "aogrqsdmzohydtuotjyysttlknmqdyvdpbxftatuqastvphoahpsdifnxrfbqaghdfoyhhsxhntdcctcmiupqzeqsjrkmzrphfoooioyvjdxnwbzhvqzwuprgi" +
                "bsitjpazfritpfesfsqgrxekxcgmtmvvgfqqwspdjxzaddukvlqpkuzjzhwsutpcafsyaibjhammegwbtpqelrnkbldxguzgcseccinlizyogwqzlifxcthdgmanjz" +
                "tltanviajschhkdxlrfrohmxmsmmhvodihdvfnxofbxmlclxvrojacjpwxbeurhcbmzgzwwgyvtlzeivxygaappzosdikkmlwltxirdioytnfeieepehgvgvqjfavsnt" +
                "fiqnbvxputczznfdcmkkhshxdnzyhormwjcxfbobwrcvehbitnsdgacjpeiysbmrnoqssfvoyxkeglmaygfgihqznazgdmzqcpiuudjucvyjimlivqpdzhfnhevksudvj" +
                "lrgrcavxzehlrqgjhmjqtyzztjsnopyagetjfqiexqroiayrojhjhgiarcpgrniysdhztpfqhwhpyfioopxxvgxniovabdatgjszazsiwzzweiluxirvqqkzefbhiddqmx" +
                "rmxjwtiwrogckdldadtkczpfhzikpujhjgqfbbbtrhvcnifnmbaqapyjrgvgdfpmlirnjvgaedetokjcljsnaqzrzuacbpqnxjciekllnpedbpfoyyczqdspxstbknwl" +
                "rbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmcoqhnwnkuewhsqmgbbuqcljjivswmdkqtbxixmvtrrblj" +
                "ptnsnfwzqfjmafadrrwsofsbcnuvqhffbsaqxwpqcacehchzvfrkmlnozjkpqpxrjxkitzyxacbhhkicqcoendtomfgdwdwfcgpxiqvkuytdlcgdewhtaciohord" +
                "tqkvwcsgspqoqmsboaguwnnyqxnzlgdgwpbtrwblnsadeuguumoqcdrubetokyxhoachwdvmxxrdryxlmndqtukwagmlejuukwcibxubumenmeyatdrmydiajxloghi" +
                "qfmzhlvihjouvsuyoypayulyeimuotehzriicfskpggkbbipzzrzucxamludfykgruowzgiooobppleqlwphapjnadqhdcnvwdtxjbmyppphauxnspusgdhiixqmbf" +
                "jxjcvudjsuyibyebmwsiqyoygyxymzevypzvjegebeocfuftsxdixtigsieehkchzdflilrjqfnxztqrsvbspkyhsenbppkqtpddbuotbbqcwivrfxjujjddntgeiqvdg" +
                "aijvwcyaubwewpjvygehljxepbpiwuqzdzubdubzvafspqpqwuzifwovyddwyvvburczmgyjgfdxvtnunneslsplwuiupfxlzbknhkwppanltcfirjcddsozoyvegurfwcsfm" +
                "oxeqmrjowrghwlkobmeahkgccnaehhsveymqpxhlrnunyfdzrhbasjeuygafoubutpnimuwfjqsjxvkqdorxxvrwctdsneogvbpkxlpgdirbfcriqifpgynkrrefxsnvuc" +
                "ftpwctgtwmxnupycfgcuqunublmoiitncklefszbexrampetvhqnddjeqvuygpnkazqfrpjvoaxdpcwmjobmskskfojnewxgxnnofwltwjwnnvbwjckdmeouuzhyvhgv" +
                "wujbqxxpitcvograiddvhrrdsycqhkleewhxtembaqwqwpqhsuebnvfgvjwdvjjafqzzxlcxdzncqgjlapopkvxfgvicetcmkbljopgtqvvhbgsdvivhesnkqxmwrq" +
                "idrvmhlubbryktheyentmrobdeyqcrgluaiihveixwjjrqopubjguxhxdipfzwswybgfylqvjzharvrlyauuzdrcnjkphclffrkeecbpdipufhidjcxjhrnxcxmjcx" +
                "ohqanxdrmgzebhnlmwpmhwdvthsfqueeexgrujigskmvrzgfwvrftwapdtutpbztygnsrxajjngcomikjzsdwssznovdruypcnjulkfuzmxnafamespckjcazxdr" +
                "tdgyrqscczybnvqqcqcjitlvcnvbmasidzgwraatzzwpwmfbfjkncvkelhhzjchpdnlunmppnlgjznkewwuysgefonexpmmsbaopmdgzqmkqzxuvtqvnxbslqzkg" +
                "lzamzpdnsjolvybwxxttqognrbaiakqllszkhfzconnmoqklpeefsnsmouwqhodsgcfohesyshmgxwtoayuvnojdjftqtwkbapriujimqwspslgvlcsaqbdbgwtbs" +
                "eettwdnfnbyjvpdjxyuzqxstatbzpctthoofremgfkrbcvkzvgbofthgojhdnaywpnbitoraaibednezwfpdawlohssvtqtkfvsyljzlucqxswyqdntdmfrtzlqsekejh" +
                "zksklfepxchvczysvdgcxbbiswmeaylzifktmoikssfxtgpojxqiysrsqfwqdjqnqcgdqrnlluieazvmwnuufnnxvloyvgmliuqandlyavfauaosnlnvacsvpiumoia" +
                "wcqxswkqwgxyazntnaikameybnuqbcqaggxachrynqxqqmlfotpqhvokiiammqmvxjvbsoaifzyxnjcberrnmixxsyjhovengbpyqrixqgwdrygxrxkfhicainhw" +
                "ilkqmbpeszdigznzxtzqsjwatycbmjawwmninepfduplucltxmkpvgrrgtuseurageltkcapwpbqromqawixezqkvlfbhwcocpjmrmbpbegvsuluqtuuvkesvj" +
                "tdhvtjmexfqbvufdpaxcwnwqjtbplyzedicwsodpwtqrpyuearhwgfnpaqelofrsotqiktxipqzeqvlqmuoubbjbrpmixfclbstnosvdkujcpwsdqhxrkiuez" +
                "iowoqjpiecwxxbjtnmkjgncpmvauqgtausokbfugjtfiuqbjclvlazamucimicnewdoxjlfuemdadgkhufsuevjaxrnivcorhfrqqwnujquoyevslqprlyskrhun" +
                "ljgsoxleuyyfqutozqhmgyetyyepfaesjlkzivdevdllygazxjndjrxhrdyyddqnqdoayshwxshxzjywumbffamxdnxjqoyirmirernekxdlicjfqkkvnxuqmszc" +
                "ixmzkwsqoiwyfalpeuuugfrteomqinuqnirxelpstosaodqszkogrfbxtnpdbltqtmpyyeqtujuiokcowswqyxntndxqqsgkhvihbaawjugagloddktdjizynyoes" +
                "uozryityjrifximkyrokktvusuiqiojfckyatryekijksvusokcyeavwufpctajxkixdbxjmitwcqqxfbbfhbadvfuaaujxfrwkvuuhepdifvfkyhsfiuleafgaap" +
                "ahjwtesplweqfmnmymtqreleinkopmfpvomqueghdmxkynwxzqnswaxgnjwdxbuusgkmnqwqdvadiwahoqakqzqgkmlhqfdimnwzgsplorownpgehioxhhfrvqalw" +
                "dtksslykajataxgpdmyldxukdnftprrumbmemlrowrhwoqntclghlcrorzhgsbaecplpccdyvnxmdmfhaoplqizkhiqbjtimitdkxiksxjecwmkwabhslievqvwcq" +
                "eqaztkydwrbgxdcjpalshgepkzhhvlxcbxdwjccgtdoqiscyspqzvuqivzptlpvooynyapgvswoaosaghrffnxnjyeeltzaizniccozwknwyhzgpqlwfkjqipuuj" +
                "vwtxlbznryjdohbvghmyuiggtyqjtmuqinntqmihntkddnalwnmsxsatqqeldacnnpjfermrnyuqnwbjjpdjhdeavknykpoxhxclqqedqavdwzoiorrwwxyrhls" +
                "rdgqkduvtmzzczufvtvfioygkvedervvudneghbctcbxdxezrzgbpfhzanffeccbgqfmzjqtlrsppxqiywjobspefujlxnmddurddiyobqfspvcoulcvdrzkmkwlyiqd" +
                "chghrgytzdnobqcvdeqjystmepxcaniewqmoxkjwpymqorluxedvywhcoghotpusfgiestckrpaigocfufbubiyrrffmwaeeimidfnnzcphkflpbqsvtdwludsgaungfzo" +
                "ihbxifoprwcjzsdxngtacwtjypweuxvegybogsfxqhipbompufpxckicaghufczmaccgwigmrqcteqkbwbaamicoqlivnjjoomwkucznvdgztqarsargkwuaheyvohle" +
                "tjqpopdjslkoelfynzeavaaceazuimydypvmgyxblhppuunkttkqtmvanuuvjvahmvvuvsvhzkywhmgchqvdcqdpmzmxwneikzfgtantnlpwzvahnvkplpfaotx" +
                "ngexrfczzdmuszlobiokvkwkxlrxblvotzomeqlftxzlzkbcsqmnciazvrzyeupyvdkbtwhpvgcwteylwkbyubxruwszshxpmjrhfawdibzbfypdksbhtaapzsorbn" +
                "jpzcxecvjmwjqdjhgvzjcukfjjzacbpnsoppvtleijpynyfvuefyyrdjadjegbsypjomfbrnkilzhsvbwczwtdfvirbosvmjfcymdrzqzkpgemjaojyjofeywimqdackdawit" +
                "xysjqzncipttncjtjhrtvkwwojbqhjjfkboaccenrxihcsanbtgxdcttnujvfscrwqtyuynmxwvbqxorquowzhpmdzjlrlcncyoywbmvzhxpenhvivthfivkhfxbqaquyetw" +
                "ifthnsxrggoqbhxiqsvzzscqutmszfqjnmtaeqtmykcbrzkjuhltznluiyokfhvstouzgqmeaogrqsdmzohydtuotjyysttlknmqdyvdpbxftatuqastvphoahpsdifnxrfbqa" +
                "ghdfoyhhsxhntdcctcmiupqzeqsjrkmzrphfoooioyvjdxnwbzhvqzwuprgibsitjpazfritpfesfsqgrxekxcgmtmvvgfqqwspdjxzaddukvlqpkuzjzhwsutpcafsyai" +
                "bjhammegwbtpqelrnkbldxguzgcseccinlizyogwqzlifxcthdgmanjztltanviajschhkdxlrfrohmxmsmmhvodihdvfnxofbxmlclxvrojacjpwxbeurhcbmzgzwwgyv" +
                "tlzeivxygaappzosdikkmlwltxirdioytnfeieepehgvgvqjfavsntfiqnbvxputczznfdcmkkhshxdnzyhormwjcxfbobwrcvehbitnsdgacjpeiysbmrnoqssfvoyxke" +
                "glmaygfgihqznazgdmzqcpiuudjucvyjimlivqpdzhfnhevksudvjlrgrcavxzehlrqgjhmjqtyzztjsnopyagetjfqiexqroiayrojhjhgiarcpgrniysdhztpfqhwhpyf" +
                "ioopxxvgxniovabdatgjszazsiwzzweiluxirvqqkzefbhiddqmxrmxjwtiwrogckdldadtkczpfhzikpujhjgqfbbbtrhvcnifnmbaqapyjrgvgdfpmlirnjvgaedetokj" +
                "cljsnaqzrzuacbpqnxjciekllnpedbpfoyyczqdspxstbk");
        System.out.println(res);
    }






    public boolean repeatedSubstringPattern(String s) {
        if(s == null || s.length() == 0) return true;
        int len = s.length();
        for(int i = 1; i < len; i ++){
            if(len % i == 0){
                String sub = s.substring(0, i);
                int j = i;
                for(; j < len; j += i){
                    if(!sub.equals(s.substring(j, j + i))){
                        break;
                    }
                }
                if(j >= len) return true;
            }
        }
        return false;
    }
}
