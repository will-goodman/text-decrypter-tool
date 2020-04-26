package com.willgoodman.model.algorithms.decryption.frequency;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;


public class NearestFrequencyTest {

    @Test
    public void decrypt() {
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
        characterFrequencies.put('x', 0.150f);
        characterFrequencies.put('q', 0.1f);
        characterFrequencies.put('j', 0.153f);
        characterFrequencies.put('z', 0.074f);

        NearestFrequency nearestFrequency = new NearestFrequency(characterFrequencies);
        String plainText = nearestFrequency.decrypt("Gb Fureybpx Ubyzrf fur vf nyjnlf gur jbzna. V unir fryqbz urneq uvz zragvba ure haqre nal bgure anzr. Va uvf rlrf fur rpyvcfrf naq cerqbzvangrf gur jubyr bs ure frk. Vg jnf abg gung ur sryg nal rzbgvba nxva gb ybir sbe Verar Nqyre. Nyy rzbgvbaf, naq gung bar cnegvphyneyl, jrer noubeerag gb uvf pbyq, cerpvfr ohg nqzvenoyl onynaprq zvaq. Ur jnf, V gnxr vg, gur zbfg cresrpg ernfbavat naq bofreivat znpuvar gung gur jbeyq unf frra, ohg nf n ybire ur jbhyq unir cynprq uvzfrys va n snyfr cbfvgvba. Ur arire fcbxr bs gur fbsgre cnffvbaf, fnir jvgu n tvor naq n farre. Gurl jrer nqzvenoyr guvatf sbe gur bofreire--rkpryyrag sbe qenjvat gur irvy sebz zra'f zbgvirf naq npgvbaf. Ohg sbe gur genvarq ernfbare gb nqzvg fhpu vagehfvbaf vagb uvf bja qryvpngr naq svaryl nqwhfgrq grzcrenzrag jnf gb vagebqhpr n qvfgenpgvat snpgbe juvpu zvtug guebj n qbhog hcba nyy uvf zragny erfhygf. Tevg va n frafvgvir vafgehzrag, be n penpx va bar bs uvf bja uvtu-cbjre yrafrf, jbhyq abg or zber qvfgheovat guna n fgebat rzbgvba va n angher fhpu nf uvf. Naq lrg gurer jnf ohg bar jbzna gb uvz, naq gung jbzna jnf gur yngr Verar Nqyre, bs qhovbhf naq dhrfgvbanoyr zrzbel. V unq frra yvggyr bs Ubyzrf yngryl.");

        assert plainText.equals("zk byqpgkci ykgwqb byq vb xgmxsb zyq mkwxj. v yxnq bqgfkw yqxpf yvw wqjzvkj yqp ujfqp xjs kzyqp jxwq. vj yvb qsqb byq qcgvrbqb xjf rpqfkwvjxzqb zyq mykgq kl yqp bqo. vz mxb jkz zyxz yq lqgz xjs qwkzvkj xivj zk gknq lkp vpqjq xfgqp. xgg qwkzvkjb, xjf zyxz kjq rxpzvcugxpgs, mqpq xdykppqjz zk yvb ckgf, rpqcvbq duz xfwvpxdgs dxgxjcqf wvjf. yq mxb, v zxiq vz, zyq wkbz rqplqcz pqxbkjvjh xjf kdbqpnvjh wxcyvjq zyxz zyq mkpgf yxb bqqj, duz xb x gknqp yq mkugf yxnq rgxcqf yvwbqgl vj x lxgbq rkbvzvkj. yq jqnqp brkiq kl zyq bklzqp rxbbvkjb, bxnq mvzy x hvdq xjf x bjqqp. zyqs mqpq xfwvpxdgq zyvjhb lkp zyq kdbqpnqp--qocqggqjz lkp fpxmvjh zyq nqvg lpkw wqj'b wkzvnqb xjf xczvkjb. duz lkp zyq zpxvjqf pqxbkjqp zk xfwvz bucy vjzpubvkjb vjzk yvb kmj fqgvcxzq xjf lvjqgs xfaubzqf zqwrqpxwqjz mxb zk vjzpkfucq x fvbzpxczvjh lxczkp myvcy wvhyz zypkm x fkudz urkj xgg yvb wqjzxg pqbugzb. hpvz vj x bqjbvzvnq vjbzpuwqjz, kp x cpxci vj kjq kl yvb kmj yvhy-rkmqp gqjbqb, mkugf jkz dq wkpq fvbzupdvjh zyxj x bzpkjh qwkzvkj vj x jxzupq bucy xb yvb. xjf sqz zyqpq mxb duz kjq mkwxj zk yvw, xjf zyxz mkwxj mxb zyq gxzq vpqjq xfgqp, kl fudvkub xjf tuqbzvkjxdgq wqwkps. v yxf bqqj gvzzgq kl ykgwqb gxzqgs.");
    }

    @Test
    public void decryptFile() {
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
        characterFrequencies.put('x', 0.150f);
        characterFrequencies.put('q', 0.1f);
        characterFrequencies.put('j', 0.153f);
        characterFrequencies.put('z', 0.074f);

        NearestFrequency nearestFrequency = new NearestFrequency(characterFrequencies);
        try {
            nearestFrequency.decrypt("cipher.txt", "output.txt");
        } catch (IOException ex) {
            System.out.println("ERROR");
            assert false;
        }
    }
}