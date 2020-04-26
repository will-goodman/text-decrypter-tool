package com.willgoodman.model;

import com.willgoodman.model.algorithms.decryption.caesar.ROT13;
import com.willgoodman.model.algorithms.decryption.frequency.FrequencyAnalysis;
import com.willgoodman.model.algorithms.decryption.frequency.NearestFrequency;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

public class ModelTest {

    @Test
    public void decryptROT13() {
        Model model = new Model();

        String plainText = model.decrypt("Gb Fureybpx Ubyzrf fur vf nyjnlf gur jbzna. V unir fryqbz urneq uvz zragvba ure haqre nal bgure anzr. Va uvf rlrf fur rpyvcfrf naq cerqbzvangrf gur jubyr bs ure frk. Vg jnf abg gung ur sryg nal rzbgvba nxva gb ybir sbe Verar Nqyre. Nyy rzbgvbaf, naq gung bar cnegvphyneyl, jrer noubeerag gb uvf pbyq, cerpvfr ohg nqzvenoyl onynaprq zvaq. Ur jnf, V gnxr vg, gur zbfg cresrpg ernfbavat naq bofreivat znpuvar gung gur jbeyq unf frra, ohg nf n ybire ur jbhyq unir cynprq uvzfrys va n snyfr cbfvgvba. Ur arire fcbxr bs gur fbsgre cnffvbaf, fnir jvgu n tvor naq n farre. Gurl jrer nqzvenoyr guvatf sbe gur bofreire--rkpryyrag sbe qenjvat gur irvy sebz zra'f zbgvirf naq npgvbaf. Ohg sbe gur genvarq ernfbare gb nqzvg fhpu vagehfvbaf vagb uvf bja qryvpngr naq svaryl nqwhfgrq grzcrenzrag jnf gb vagebqhpr n qvfgenpgvat snpgbe juvpu zvtug guebj n qbhog hcba nyy uvf zragny erfhygf. Tevg va n frafvgvir vafgehzrag, be n penpx va bar bs uvf bja uvtu-cbjre yrafrf, jbhyq abg or zber qvfgheovat guna n fgebat rzbgvba va n angher fhpu nf uvf. Naq lrg gurer jnf ohg bar jbzna gb uvz, naq gung jbzna jnf gur yngr Verar Nqyre, bs qhovbhf naq dhrfgvbanoyr zrzbel. V unq frra yvggyr bs Ubyzrf yngryl.", ROT13.NAME);

        assert plainText.equals("To Sherlock Holmes she is always the woman. I have seldom heard him mention her under any other name. In his eyes she eclipses and predominates the whole of her sex. It was not that he felt any emotion akin to love for Irene Adler. All emotions, and that one particularly, were abhorrent to his cold, precise but admirably balanced mind. He was, I take it, the most perfect reasoning and observing machine that the world has seen, but as a lover he would have placed himself in a false position. He never spoke of the softer passions, save with a gibe and a sneer. They were admirable things for the observer--excellent for drawing the veil from men's motives and actions. But for the trained reasoner to admit such intrusions into his own delicate and finely adjusted temperament was to introduce a distracting factor which might throw a doubt upon all his mental results. Grit in a sensitive instrument, or a crack in one of his own high-power lenses, would not be more disturbing than a strong emotion in a nature such as his. And yet there was but one woman to him, and that woman was the late Irene Adler, of dubious and questionable memory. I had seen little of Holmes lately.");
    }


    @Test
    public void decryptFileROT13() {
        try {
            Model model = new Model();
            model.decrypt("srcFile", "outFile", ROT13.NAME);
        } catch (IOException ex) {
            assert false;
        }
    }


    @Test
    public void decryptFrequencyAnalysis() {
        Model model = new Model();

        HashMap<Character, Float> characterFrequencies = new HashMap<>();
        characterFrequencies.put('e', 12.7f);
        characterFrequencies.put('t', 9.01f);
        characterFrequencies.put('a', 8.167f);
        characterFrequencies.put('o', 7.7f);
        characterFrequencies.put('i', 7.3f);
        characterFrequencies.put('n', 6.75f);
        characterFrequencies.put('s', 6.33f);
        characterFrequencies.put('r', 6.0f);
        characterFrequencies.put('h', 6.094f);
        characterFrequencies.put('d', 4.253f);
        characterFrequencies.put('l', 4.025f);
        characterFrequencies.put('u', 2.758f);
        characterFrequencies.put('c', 2.782f);
        characterFrequencies.put('m', 2.406f);
        characterFrequencies.put('f', 2.228f);
        characterFrequencies.put('y', 1.974f);
        characterFrequencies.put('w', 2.360f);
        characterFrequencies.put('g', 2.015f);
        characterFrequencies.put('p', 1.929f);
        characterFrequencies.put('b', 1.492f);
        characterFrequencies.put('v', 0.978f);
        characterFrequencies.put('k', 0.772f);
        characterFrequencies.put('j', 0.153f);
        characterFrequencies.put('x', 0.150f);
        characterFrequencies.put('q', 0.1f);
        characterFrequencies.put('z', 0.074f);

        String plainText = model.decrypt("Gb Fureybpx Ubyzrf fur vf nyjnlf gur jbzna. V unir fryqbz urneq uvz zragvba ure haqre nal bgure anzr. Va uvf rlrf fur rpyvcfrf naq cerqbzvangrf gur jubyr bs ure frk. Vg jnf abg gung ur sryg nal rzbgvba nxva gb ybir sbe Verar Nqyre. Nyy rzbgvbaf, naq gung bar cnegvphyneyl, jrer noubeerag gb uvf pbyq, cerpvfr ohg nqzvenoyl onynaprq zvaq. Ur jnf, V gnxr vg, gur zbfg cresrpg ernfbavat naq bofreivat znpuvar gung gur jbeyq unf frra, ohg nf n ybire ur jbhyq unir cynprq uvzfrys va n snyfr cbfvgvba. Ur arire fcbxr bs gur fbsgre cnffvbaf, fnir jvgu n tvor naq n farre. Gurl jrer nqzvenoyr guvatf sbe gur bofreire--rkpryyrag sbe qenjvat gur irvy sebz zra'f zbgvirf naq npgvbaf. Ohg sbe gur genvarq ernfbare gb nqzvg fhpu vagehfvbaf vagb uvf bja qryvpngr naq svaryl nqwhfgrq grzcrenzrag jnf gb vagebqhpr n qvfgenpgvat snpgbe juvpu zvtug guebj n qbhog hcba nyy uvf zragny erfhygf. Tevg va n frafvgvir vafgehzrag, be n penpx va bar bs uvf bja uvtu-cbjre yrafrf, jbhyq abg or zber qvfgheovat guna n fgebat rzbgvba va n angher fhpu nf uvf. Naq lrg gurer jnf ohg bar jbzna gb uvz, naq gung jbzna jnf gur yngr Verar Nqyre, bs qhovbhf naq dhrfgvbanoyr zrzbel. V unq frra yvggyr bs Ubyzrf yngryl.", FrequencyAnalysis.NAME, characterFrequencies);

        assert plainText.equals("ti srehdiwk ridces sre ns aduabs tre uicao. n rave sedlic reahl rnc ceotnio reh moleh aob itreh oace. no rns ebes sre ewdnyses aol yhelicnoates tre uride if reh sej. nt uas oit trat re fedt aob ecitnio akno ti dive fih nheoe aldeh. add ecitnios, aol trat ioe yahtnwmdahdb, uehe agrihheot ti rns widl, yhewnse gmt alcnhagdb gadaowel cnol. re uas, n take nt, tre cist yehfewt heasionop aol igsehvnop cawrnoe trat tre uihdl ras seeo, gmt as a diveh re uimdl rave ydawel rncsedf no a fadse yisntnio. re oeveh syike if tre sifteh yassnios, save untr a pnge aol a soeeh. treb uehe alcnhagde trnops fih tre igsehveh--ejweddeot fih lhaunop tre vend fhic ceo's citnves aol awtnios. gmt fih tre thanoel heasioeh ti alcnt smwr nothmsnios noti rns iuo lednwate aol fnoedb alxmstel tecyehaceot uas ti nothilmwe a lnsthawtnop fawtih urnwr cnprt trhiu a limgt myio add rns ceotad hesmdts. phnt no a seosntnve nosthmceot, ih a whawk no ioe if rns iuo rnpr-yiueh deoses, uimdl oit ge cihe lnstmhgnop trao a sthiop ecitnio no a oatmhe smwr as rns. aol bet trehe uas gmt ioe uicao ti rnc, aol trat uicao uas tre date nheoe aldeh, if lmgnims aol qmestnioagde cecihb. n ral seeo dnttde if ridces datedb.");
    }


    @Test
    public void decryptFileFrequencyAnalysis() {
        HashMap<Character, Float> characterFrequencies = new HashMap<>();
        characterFrequencies.put('e', 12.7f);
        characterFrequencies.put('t', 9.01f);
        characterFrequencies.put('a', 8.167f);
        characterFrequencies.put('o', 7.7f);
        characterFrequencies.put('i', 7.3f);
        characterFrequencies.put('n', 6.75f);
        characterFrequencies.put('s', 6.33f);
        characterFrequencies.put('r', 6.0f);
        characterFrequencies.put('h', 6.094f);
        characterFrequencies.put('d', 4.253f);
        characterFrequencies.put('l', 4.025f);
        characterFrequencies.put('u', 2.758f);
        characterFrequencies.put('c', 2.782f);
        characterFrequencies.put('m', 2.406f);
        characterFrequencies.put('f', 2.228f);
        characterFrequencies.put('y', 1.974f);
        characterFrequencies.put('w', 2.360f);
        characterFrequencies.put('g', 2.015f);
        characterFrequencies.put('p', 1.929f);
        characterFrequencies.put('b', 1.492f);
        characterFrequencies.put('v', 0.978f);
        characterFrequencies.put('k', 0.772f);
        characterFrequencies.put('j', 0.153f);
        characterFrequencies.put('x', 0.150f);
        characterFrequencies.put('q', 0.1f);
        characterFrequencies.put('z', 0.074f);

        try {
            Model model = new Model();
            model.decrypt("srcFile", "outFile", FrequencyAnalysis.NAME, characterFrequencies);
        } catch (IOException ex) {
            assert false;
        }
    }


    @Test
    public void decryptNearestFrequency() {
        Model model = new Model();

        HashMap<Character, Float> characterFrequencies = new HashMap<>();
        characterFrequencies.put('e', 12.7f);
        characterFrequencies.put('t', 9.01f);
        characterFrequencies.put('a', 8.167f);
        characterFrequencies.put('o', 7.7f);
        characterFrequencies.put('i', 7.3f);
        characterFrequencies.put('n', 6.75f);
        characterFrequencies.put('s', 6.33f);
        characterFrequencies.put('r', 6.0f);
        characterFrequencies.put('h', 6.094f);
        characterFrequencies.put('d', 4.253f);
        characterFrequencies.put('l', 4.025f);
        characterFrequencies.put('u', 2.758f);
        characterFrequencies.put('c', 2.782f);
        characterFrequencies.put('m', 2.406f);
        characterFrequencies.put('f', 2.228f);
        characterFrequencies.put('y', 1.974f);
        characterFrequencies.put('w', 2.360f);
        characterFrequencies.put('g', 2.015f);
        characterFrequencies.put('p', 1.929f);
        characterFrequencies.put('b', 1.492f);
        characterFrequencies.put('v', 0.978f);
        characterFrequencies.put('k', 0.772f);
        characterFrequencies.put('j', 0.153f);
        characterFrequencies.put('x', 0.150f);
        characterFrequencies.put('q', 0.1f);
        characterFrequencies.put('z', 0.074f);

        String plainText = model.decrypt("Gb Fureybpx Ubyzrf fur vf nyjnlf gur jbzna. V unir fryqbz urneq uvz zragvba ure haqre nal bgure anzr. Va uvf rlrf fur rpyvcfrf naq cerqbzvangrf gur jubyr bs ure frk. Vg jnf abg gung ur sryg nal rzbgvba nxva gb ybir sbe Verar Nqyre. Nyy rzbgvbaf, naq gung bar cnegvphyneyl, jrer noubeerag gb uvf pbyq, cerpvfr ohg nqzvenoyl onynaprq zvaq. Ur jnf, V gnxr vg, gur zbfg cresrpg ernfbavat naq bofreivat znpuvar gung gur jbeyq unf frra, ohg nf n ybire ur jbhyq unir cynprq uvzfrys va n snyfr cbfvgvba. Ur arire fcbxr bs gur fbsgre cnffvbaf, fnir jvgu n tvor naq n farre. Gurl jrer nqzvenoyr guvatf sbe gur bofreire--rkpryyrag sbe qenjvat gur irvy sebz zra'f zbgvirf naq npgvbaf. Ohg sbe gur genvarq ernfbare gb nqzvg fhpu vagehfvbaf vagb uvf bja qryvpngr naq svaryl nqwhfgrq grzcrenzrag jnf gb vagebqhpr n qvfgenpgvat snpgbe juvpu zvtug guebj n qbhog hcba nyy uvf zragny erfhygf. Tevg va n frafvgvir vafgehzrag, be n penpx va bar bs uvf bja uvtu-cbjre yrafrf, jbhyq abg or zber qvfgheovat guna n fgebat rzbgvba va n angher fhpu nf uvf. Naq lrg gurer jnf ohg bar jbzna gb uvz, naq gung jbzna jnf gur yngr Verar Nqyre, bs qhovbhf naq dhrfgvbanoyr zrzbel. V unq frra yvggyr bs Ubyzrf yngryl.", NearestFrequency.NAME, characterFrequencies);

        assert plainText.equals("zk byqpgkci ykgwqb byq vb xgmxsb zyq mkwxj. v yxnq bqgfkw yqxpf yvw wqjzvkj yqp ujfqp xjs kzyqp jxwq. vj yvb qsqb byq qcgvrbqb xjf rpqfkwvjxzqb zyq mykgq kl yqp bqo. vz mxb jkz zyxz yq lqgz xjs qwkzvkj xivj zk gknq lkp vpqjq xfgqp. xgg qwkzvkjb, xjf zyxz kjq rxpzvcugxpgs, mqpq xdykppqjz zk yvb ckgf, rpqcvbq duz xfwvpxdgs dxgxjcqf wvjf. yq mxb, v zxiq vz, zyq wkbz rqplqcz pqxbkjvjh xjf kdbqpnvjh wxcyvjq zyxz zyq mkpgf yxb bqqj, duz xb x gknqp yq mkugf yxnq rgxcqf yvwbqgl vj x lxgbq rkbvzvkj. yq jqnqp brkiq kl zyq bklzqp rxbbvkjb, bxnq mvzy x hvdq xjf x bjqqp. zyqs mqpq xfwvpxdgq zyvjhb lkp zyq kdbqpnqp--qocqggqjz lkp fpxmvjh zyq nqvg lpkw wqj'b wkzvnqb xjf xczvkjb. duz lkp zyq zpxvjqf pqxbkjqp zk xfwvz bucy vjzpubvkjb vjzk yvb kmj fqgvcxzq xjf lvjqgs xfaubzqf zqwrqpxwqjz mxb zk vjzpkfucq x fvbzpxczvjh lxczkp myvcy wvhyz zypkm x fkudz urkj xgg yvb wqjzxg pqbugzb. hpvz vj x bqjbvzvnq vjbzpuwqjz, kp x cpxci vj kjq kl yvb kmj yvhy-rkmqp gqjbqb, mkugf jkz dq wkpq fvbzupdvjh zyxj x bzpkjh qwkzvkj vj x jxzupq bucy xb yvb. xjf sqz zyqpq mxb duz kjq mkwxj zk yvw, xjf zyxz mkwxj mxb zyq gxzq vpqjq xfgqp, kl fudvkub xjf tuqbzvkjxdgq wqwkps. v yxf bqqj gvzzgq kl ykgwqb gxzqgs.");
    }


    @Test
    public void decryptFileNearestFrequency() {
        HashMap<Character, Float> characterFrequencies = new HashMap<>();
        characterFrequencies.put('e', 12.7f);
        characterFrequencies.put('t', 9.01f);
        characterFrequencies.put('a', 8.167f);
        characterFrequencies.put('o', 7.7f);
        characterFrequencies.put('i', 7.3f);
        characterFrequencies.put('n', 6.75f);
        characterFrequencies.put('s', 6.33f);
        characterFrequencies.put('r', 6.0f);
        characterFrequencies.put('h', 6.094f);
        characterFrequencies.put('d', 4.253f);
        characterFrequencies.put('l', 4.025f);
        characterFrequencies.put('u', 2.758f);
        characterFrequencies.put('c', 2.782f);
        characterFrequencies.put('m', 2.406f);
        characterFrequencies.put('f', 2.228f);
        characterFrequencies.put('y', 1.974f);
        characterFrequencies.put('w', 2.360f);
        characterFrequencies.put('g', 2.015f);
        characterFrequencies.put('p', 1.929f);
        characterFrequencies.put('b', 1.492f);
        characterFrequencies.put('v', 0.978f);
        characterFrequencies.put('k', 0.772f);
        characterFrequencies.put('j', 0.153f);
        characterFrequencies.put('x', 0.150f);
        characterFrequencies.put('q', 0.1f);
        characterFrequencies.put('z', 0.074f);

        try {
            Model model = new Model();
            model.decrypt("srcFile", "outFile", NearestFrequency.NAME, characterFrequencies);
        } catch (IOException ex) {
            assert false;
        }
    }

}