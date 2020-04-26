package com.willgoodman.model.algorithms.decryption.frequency;

import org.junit.Test;

import java.io.IOException;
import java.util.LinkedHashSet;

public class FrequencyAnalysisTest {

    @Test
    public void decrypt() {
        LinkedHashSet<Character> mostFrequentCharacters = new LinkedHashSet();
        mostFrequentCharacters.add('e');
        mostFrequentCharacters.add('t');
        mostFrequentCharacters.add('a');
        mostFrequentCharacters.add('o');
        mostFrequentCharacters.add('i');
        mostFrequentCharacters.add('n');
        mostFrequentCharacters.add('s');
        mostFrequentCharacters.add('h');
        mostFrequentCharacters.add('r');
        mostFrequentCharacters.add('d');
        mostFrequentCharacters.add('l');
        mostFrequentCharacters.add('c');
        mostFrequentCharacters.add('u');
        mostFrequentCharacters.add('m');
        mostFrequentCharacters.add('w');
        mostFrequentCharacters.add('f');
        mostFrequentCharacters.add('g');
        mostFrequentCharacters.add('y');
        mostFrequentCharacters.add('p');
        mostFrequentCharacters.add('b');
        mostFrequentCharacters.add('v');
        mostFrequentCharacters.add('k');
        mostFrequentCharacters.add('j');
        mostFrequentCharacters.add('x');
        mostFrequentCharacters.add('q');
        mostFrequentCharacters.add('z');

        FrequencyAnalysis frequencyAnalysis = new FrequencyAnalysis(mostFrequentCharacters);
        String plainText = frequencyAnalysis.decrypt("Gb Fureybpx Ubyzrf fur vf nyjnlf gur jbzna. V unir fryqbz urneq uvz zragvba ure haqre nal bgure anzr. Va uvf rlrf fur rpyvcfrf naq cerqbzvangrf gur jubyr bs ure frk. Vg jnf abg gung ur sryg nal rzbgvba nxva gb ybir sbe Verar Nqyre. Nyy rzbgvbaf, naq gung bar cnegvphyneyl, jrer noubeerag gb uvf pbyq, cerpvfr ohg nqzvenoyl onynaprq zvaq. Ur jnf, V gnxr vg, gur zbfg cresrpg ernfbavat naq bofreivat znpuvar gung gur jbeyq unf frra, ohg nf n ybire ur jbhyq unir cynprq uvzfrys va n snyfr cbfvgvba. Ur arire fcbxr bs gur fbsgre cnffvbaf, fnir jvgu n tvor naq n farre. Gurl jrer nqzvenoyr guvatf sbe gur bofreire--rkpryyrag sbe qenjvat gur irvy sebz zra'f zbgvirf naq npgvbaf. Ohg sbe gur genvarq ernfbare gb nqzvg fhpu vagehfvbaf vagb uvf bja qryvpngr naq svaryl nqwhfgrq grzcrenzrag jnf gb vagebqhpr n qvfgenpgvat snpgbe juvpu zvtug guebj n qbhog hcba nyy uvf zragny erfhygf. Tevg va n frafvgvir vafgehzrag, be n penpx va bar bs uvf bja uvtu-cbjre yrafrf, jbhyq abg or zber qvfgheovat guna n fgebat rzbgvba va n angher fhpu nf uvf. Naq lrg gurer jnf ohg bar jbzna gb uvz, naq gung jbzna jnf gur yngr Verar Nqyre, bs qhovbhf naq dhrfgvbanoyr zrzbel. V unq frra yvggyr bs Ubyzrf yngryl.");
        System.out.println(plainText);

        assert plainText.equals("ti srehdiwk ridces sre ns aduabs tre uicao. n rave sedlic reahl rnc ceotnio reh moleh aob itreh oace. no rns ebes sre ewdnyses aol yhelicnoates tre uride if reh sej. nt uas oit trat re fedt aob ecitnio akno ti dive fih nheoe aldeh. add ecitnios, aol trat ioe yahtnwmdahdb, uehe agrihheot ti rns widl, yhewnse gmt alcnhagdb gadaowel cnol. re uas, n take nt, tre cist yehfewt heasionop aol igsehvnop cawrnoe trat tre uihdl ras seeo, gmt as a diveh re uimdl rave ydawel rncsedf no a fadse yisntnio. re oeveh syike if tre sifteh yassnios, save untr a pnge aol a soeeh. treb uehe alcnhagde trnops fih tre igsehveh--ejweddeot fih lhaunop tre vend fhic ceo's citnves aol awtnios. gmt fih tre thanoel heasioeh ti alcnt smwr nothmsnios noti rns iuo lednwate aol fnoedb alxmstel tecyehaceot uas ti nothilmwe a lnsthawtnop fawtih urnwr cnprt trhiu a limgt myio add rns ceotad hesmdts. phnt no a seosntnve nosthmceot, ih a whawk no ioe if rns iuo rnpr-yiueh deoses, uimdl oit ge cihe lnstmhgnop trao a sthiop ecitnio no a oatmhe smwr as rns. aol bet trehe uas gmt ioe uicao ti rnc, aol trat uicao uas tre date nheoe aldeh, if lmgnims aol qmestnioagde cecihb. n ral seeo dnttde if ridces datedb.");
    }

    @Test
    public void decryptFile() {
        LinkedHashSet<Character> mostFrequentCharacters = new LinkedHashSet();
        mostFrequentCharacters.add('e');
        mostFrequentCharacters.add('t');
        mostFrequentCharacters.add('a');
        mostFrequentCharacters.add('o');
        mostFrequentCharacters.add('i');
        mostFrequentCharacters.add('n');
        mostFrequentCharacters.add('s');
        mostFrequentCharacters.add('h');
        mostFrequentCharacters.add('r');
        mostFrequentCharacters.add('d');
        mostFrequentCharacters.add('l');
        mostFrequentCharacters.add('c');
        mostFrequentCharacters.add('u');
        mostFrequentCharacters.add('m');
        mostFrequentCharacters.add('w');
        mostFrequentCharacters.add('f');
        mostFrequentCharacters.add('g');
        mostFrequentCharacters.add('y');
        mostFrequentCharacters.add('p');
        mostFrequentCharacters.add('b');
        mostFrequentCharacters.add('v');
        mostFrequentCharacters.add('k');
        mostFrequentCharacters.add('j');
        mostFrequentCharacters.add('x');
        mostFrequentCharacters.add('q');
        mostFrequentCharacters.add('z');

        FrequencyAnalysis frequencyAnalysis = new FrequencyAnalysis(mostFrequentCharacters);
        try {
            frequencyAnalysis.decrypt("cipher.txt", "output.txt");
        } catch (IOException ex) {
            System.out.println("ERROR");
            assert false;
        }

    }
}