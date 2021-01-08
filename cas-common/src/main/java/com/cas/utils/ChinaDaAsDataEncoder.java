package com.cas.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author: gaoxiaokai[gao_xk@suixingpay.com]
 * @date: 2019年05月30日 下午5:29:24
 * @version: V1.0
 * @review: gaoxiaokai[gao_xk@suixingpay.com]/2019年05月30日 下午5:29:24
 */
public final class ChinaDaAsDataEncoder {
    private static ChinaDaAsDataEncoder instance;
    private Map<String, String[]> versionAndKeyPairs = new HashMap<>();
    private Map<String, byte[]> versionAndKeyMapping = new HashMap<>();

    public static String encode(String sourceString, String definedVersion) throws Exception {
        return getInstance().encodeByCurrentVersionKey(sourceString, definedVersion);
    }

    private static ChinaDaAsDataEncoder getInstance() throws Exception {
        if (instance == null) {
            instance = new ChinaDaAsDataEncoder();
        }

        return instance;
    }

    private ChinaDaAsDataEncoder() throws Exception {
        this.versionAndKeyPairs.put("V1", new String[]{"", "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAmh7PPBk78970ENx0gEmVYH+/ibZM<r><n>zv10TZ9nzpPeIdcVbVUhUW359fEHR5iz/5PIzsrJXxVuMCf73HD2+u+f0QIDAQABAkAgWglMZ0uj<r><n>dJCtHWnDKC8YE5jkUoUrCh8o0CZdQcLmPUmKXZlpKoe3WfRXO8gRHfpk00cbA+K2W5d8fzaTe35B<r><n>AiEAzhWIwJj36v5a4SEgWSxCf75nYFwfO5w/aX/a1TUdKvkCIQC/czTVKNvecBWDQ8bNsus+jcLk<r><n>jKEMl8X/6NPH/cu5mQIhAINsVDOO2buwzb/4Yiq8AQR6Jm7bEMfxErdy49lH7/vxAiEAkgfJQ9JP<r><n>q2KpNh/Lk3km8Obmwrf13AoksCei6shOzfkCIQCFd9SPhBbq4zO19GI6D4pemDiK7L2wTaYfUBWo<r><n>6FjM5Q=="});//NOSONAR
        this.versionAndKeyPairs.put("V2", new String[]{"", "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAlhmyDnoqM9xDqNEKxsAxI/OY1cbk<r><n>9r5CvqQYKvJVWBGimWTLjjfCEaOYojNS/lmQbpJxFvCPVo7LBP4Tv73CBQIDAQABAkBDJSl+ym14<r><n>l474xNKiSDVzVLRI5Vlle4UWDQf2pkNBSqc64UPv0Fx2Cu/Uxfp6svpsfGdpPWZ/Jb0OtSN8/8Hd<r><n>AiEA8GxACHTcSF71riyfO3pfTrYl+0WnTRnpiIXbn286/+MCIQCf01IOqo+3o+Ry+uKKBdi7pKWa<r><n>rdFgp6WmogJPRYYK9wIgAUuD+MewXA9u4VZKMUbSdpkSkUzpvseR83NNIIm31qMCIH02gcMbvRyS<r><n>OhgalgCsM/BOPa/BHZgrv4ucwVJ5lVphAiAZyOeI3Ijm9GzPicsaqAxyulQvq0Z9q0QkKOoQSFdW<r><n>8w=="});//NOSONAR
        this.versionAndKeyPairs.put("V3", new String[]{"", "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEArzUxh3oSF9zGouaDbYVZlJyc9eNv<r><n>+ViC+ccbFzImtOjJ+2COL+C8GHNPKMAeiz6epU3oatPPSIxSl1i89RpnuwIDAQABAkA6IXv9ZxL5<r><n>t9Zv1etlOwkttYieTIeqQyOxm83xNEKDYuuMnNYOMAGwrDOOurtO0H7sCZJyCiAifZ55loitsJyB<r><n>AiEA7zf8cpMN90RwA4mm+0VXfd95DWqGAKnqPGw2DSbcDJ8CIQC7f6qHO40SbiChxJrPJV0ToJ8v<r><n>0jFWSeQxxkvp9qJzZQIgCN6sj5sAQ5DYDmmGNX7mtZYZqPOwMEock0z33Bj6w8UCIQCsMgdOwyZA<r><n>MtvNNV5nt4zPpcgMhyHAeYQVImQ9rsBq8QIgdqSZKJZg75KbtXKUIAwtt962hzCDXWObmBAzBCXp<r><n>z/4="});//NOSONAR
        this.versionAndKeyPairs.put("V4", new String[]{"", "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAy9+2PXHzCKVUaN1/cbkFI/kxl2Ss<r><n>VGjkjWj2z5nrDVbX9/mPdgVcL+ttypd6VCTA3uvXCvi/Fq/0eLaWXH9jUwIDAQABAkAroeNU3zBO<r><n>Z2PbVZxS8HAjNM/iQM+I+TIA+bfo0l416kBgyi1z/3PBfikXHnwY39BN/lxXd0Z0soKGrywik/eh<r><n>AiEA5I0fTMk1pugqK30Mf7xr3/BTj7HbjPJ10LQY5QLFaekCIQDkW+A+G+AaThuq+NNhQKmillp4<r><n>0E4vZ3xVwZd44kjh2wIhANov01K1v8+AUknEDnufgIh/4PYMQGG1Uj4UwLNYSuiJAiAJY/nRtAXA<r><n>LywWGMtgeW9AX1xzEWXVrzS+tgpLj0qd9wIgHMHAmyxWORnw9llskyhvWB5hXIQy5c8ZnQefFQ+g<r><n>8NY="});//NOSONAR
        this.versionAndKeyPairs.put("V5", new String[]{"", "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEA4wF/T9+0eU4Mby9mLTfwJMRU6xpF<r><n>kolpyD1QwKuAZfjMx7ktQM6SkkY6ysMH64MdAiSPUdPOjYG7K69XvvQDXwIDAQABAkBUtrVVEG2g<r><n>K0E2qOCflVV30xDBn89AzSj9wewFyrqi0o9LCDyVKt5JJRRISR6NaieHHSEI/cqPWl39a5jRwfLR<r><n>AiEA+TBeeGsCrv54349zs27YVBTfNC2PGy9ylg2z5CAbP3kCIQDpNeiAS5demZjmZtmQRVmp22AW<r><n>jEC6TkZwvvbFLaprlwIhAPYNVLsCrzur+h6Fv3FF2kXr9hnHgq8NQs/359EyArXBAiEAu83+63p0<r><n>bulQuZLv9//xQhl5Ww8dfStWDKusfIPW7wcCIAFGMqgkS5SWAST40KoeNP2JI9xpRwEUe9qwatXc<r><n>pYtL"});//NOSONAR
        this.versionAndKeyPairs.put("V6", new String[]{"", "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAlBAW13ORimMBp767xg3Gj04RVW+e<r><n>CxcBrCXybKJM+lAwrZQ3gti8UFLcSTCIkQtOJd96KhOFQWbLhymoC2oabQIDAQABAkByALVlrPFq<r><n>CXNz0lNIWsEBBE4qZ2fcCBncj9dKN+lnbXrGerafPDUFyg0DEK9B/M/+CBcjz69moBYoqmCk+P6N<r><n>AiEA3atf4uIvhMXUKptoIDS+Lh3YoiMt5CdeQOYNwFJHfssCIQCq/mbve6MxmF6q9MtX6jGmDTDc<r><n>A9a2g6KYnhKPCAKspwIgVOkggbhCm4lsDCYbbLrHXsLwCKuRGKQ75oA5JEJgQKECIBe7h33loxjX<r><n>FQyVPUPOdWWenxFqVvqt+ntENT801WoxAiEAlLs9WlXdZikjiFKTpzntn7rDtnJjTkw+tfoU97p5<r><n>OeU="});//NOSONAR
        this.versionAndKeyPairs.put("V7", new String[]{"", "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAzTYwbCPzS03yIZEw7rYT3elFDIey<r><n>fKmc/ZN7o6uF2/sIZToBIHxmAhzmu8Zl8KoN+iBJMY2MyDCDYjC8YrTLTwIDAQABAkB1gxd9haS3<r><n>tb33HOaRe/Cqff9NapN6McK1rvP5SKqRygtMlWDg15D86N8MPlPxWJdixISmdScsCOpZXeP8fXhZ<r><n>AiEA7ufhdLdSOrmAIWYAsEMR1RMeHQ2d08eRFFTkT9wtOLsCIQDb5R82Jx3yQOl4if6LU4jzdUor<r><n>WygsFbiDcGzkLwrIfQIhAMIk+hipaU+Ds9ZwWBrlRjaLpKsjh6C0yvdkzW0AlldFAiAFvaqd8FGA<r><n>1J8DzZ5+prSpnoNMNHoNBJgnnlTJdEz0xQIgFP8HGOf2EgCjZZ56heuv+djPclyAcaGwO+kD/3AH<r><n>IQQ="});//NOSONAR
        this.versionAndKeyPairs.put("V8", new String[]{"", "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEA0DSRPm57BLrpczNWGH9stTVCJgYR<r><n>VhWRibgWSpeL0euYERLDau2YMx4HiryTbJzHsQ+C2gVAUMKqpnv5X6fkTQIDAQABAkEAmcgZ93j2<r><n>2BmOUI58av/DNVyvDRKyP1kS9BvKKzIgDd2VFarHxKFyilkOn/jxnPRfjIN7k32oGJsOMUwQ5UGc<r><n>qQIhAP2w6RE8Jcl4vXTp6w6fPQAyxwgqiMmhQ6Ce4i4EXtFbAiEA0hmtV8Wdk8AqQ6dDIXN9OTmp<r><n>0hyKi0coGdc614C3KXcCIQD5+pMOepg5eJInXEBUvjBbu61HrLHxDMkYwrbY5VGRNQIgVnr7PwW4<r><n>tviCbRaMK1i+Uq/JlxLodCv19a0CK3Jv8kECIBChu7NXam97tFD97ZzxiBx2g7S3YgNSTWRlqn85<r><n>Lezw"});//NOSONAR
        this.versionAndKeyPairs.put("V9", new String[]{"", "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAiDcBip7bnpBVJHB5Ul48j2oteul6<r><n>knDX9I6eMvJhRCbitHutsfqgqDfVvci7ntNhmO/7h1z3Ekhsh2gOiDO1/wIDAQABAkA8SYwnHDQ7<r><n>s8nbPOSVbhwDpYzHj7quQb9zAARIrDocfSVtyjfje86SbiZTpF1opOeit3c3YYOhso24YxrvAmkx<r><n>AiEA+AMatL77EdOXHO1Qbt/MiQQxxLZKyEXttDJg4fw8y9UCIQCMmh4BY6com8iXDH8jqYZAkckW<r><n>is/AToPabTQM1/XIgwIhAMxiAoghOtaeAFIeX8J/PBdOFZV+hHI27oUpXx3cuF1hAiBtUFFt0RWg<r><n>W+YLkfqzNlUblHzcGtZE9IZM50Rli+4akwIgfj5cmtUF8iZXTqFTAxqjBxCSuY0uuPVZBOnuAS8s<r><n>Wdg="});//NOSONAR
        this.versionAndKeyPairs.put("V10", new String[]{"", "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAsFBIVpFbvZX/QPZPsYU9iWKdzCeZ<r><n>mfP/NLikIUDs0ATXPiA+ufupQe/tvC1mj1n8qYMLwBBrfG6jPUC7Pve7twIDAQABAkAhXtCSMejf<r><n>NSierv/eZY2EFzoujcAwt1GyNNA7ywIHTNKBOH9MCcEpX0VuIzVHsZZzyvpbcHbY2VN4KEQYm8jh<r><n>AiEA6Gt75btzjBPcvzFL/2EJuwbUzBjZfi8fZW/2Qw55/fMCIQDCM5S79ymlvgpKaqvWZ2G2qyd5<r><n>4uzpT/Kb9ya35y+ILQIhAKWcAPqfaSQE+yRZEhf0oyRbJ8IOattY5Hw5HTaG2DI1AiAiWY1NUgKD<r><n>1oFreEK8wbx+2vc4j/LnscRv4d9vV+0IYQIhAMxmf10JcXtq1++wHObZilP3Kt9NCdqWQXPpmxVt<r><n>O0ZC"});//NOSONAR
        this.versionAndKeyPairs.put("V11", new String[]{"", "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAtl9/1DmiDzW2ylicAhWLnwTtY7ba<r><n>ZZFxCitBWuDY2aYLa22efNT00gPXrvAVcab9jwX5hwJUteJHGVYe8qE4DQIDAQABAkBQmjKf9Hzo<r><n>pf/MvI7u6ODVflMBAB7URwyjxuxSow+UWWxSwDkEEf5nWif7jRvRUfIFEhQC7RioXrCUT37jaMKh<r><n>AiEA5fc04uOvXSUfZtBntGoikyQSFMyeYX2p/n2nFzLrrEkCIQDLBPqBNcvD31YNvfRBZv16pQlu<r><n>eyba23WWEQvUhe/FpQIhAJCAdvX/Nilkc0fqus+ORa2J4+a38Om3ygMFN+KG97opAiArAav7USUQ<r><n>IjB9ir7CWAncQDfGQqpRdGntV0EVTSYkzQIhAIPBsjjFRp87hgIM7YUrOGzbXe3bdLyNnr3gi/dK<r><n>XEgw"});//NOSONAR
        this.versionAndKeyPairs.put("V12", new String[]{"", "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAgURajoFiBmjFYWuD5WYK7D/WGrUB<r><n>+A9PvBs6XmCrW0co2P0NJaqF9sP1PpdcnCgKyZdzs4VtEjPelpCFwgNElQIDAQABAkAOim3VUeCA<r><n>8BAj4UA48b2Qza1725iZGr/RXQ63xw//eFzBWEacM3cn0xhLi3MfuY/jHPBgdZL5tMkFIKbLRX0R<r><n>AiEAu+qC8ZNyZm1NqpBtEitEUCeEhgGazfrHls+fxM9Yq8sCIQCwGg6Y/CGBEnttUI2DsrTLABVO<r><n>vYRE1DOm3CJ76lKFHwIgIqhKxDE0fS2VkGqYoyw1qi7Po0NygKDK5MagKdUv/mcCIC5FaniAJz2G<r><n>4a+Dbuuf1SlRyEd8PHjpWPkK9nSgyTX5AiBc1wFBZUTMEHjqQtp6/Lv+hn9KbKM3u0QqEWhVaOwe<r><n>3w=="});//NOSONAR
        this.initKeyPairs();
    }

    private void initKeyPairs() throws IOException {
        Iterator entrys = this.versionAndKeyPairs.entrySet().iterator();

        while (entrys.hasNext()) {
            Entry<String, String[]> thisEntry = (Entry) entrys.next();
            String thisVersionName = (String) thisEntry.getKey();
            String[] thisKeyPair = (String[]) thisEntry.getValue();
            String privateKeyString = thisKeyPair[1];
            privateKeyString = privateKeyString.replaceAll("<n>", "\n");
            privateKeyString = privateKeyString.replaceAll("<r>", "\r");
            privateKeyString = privateKeyString.replaceAll("<t>", "\t");
            byte[] thisRSAPrivateKey = (new BASE64Decoder()).decodeBuffer(privateKeyString);
            this.versionAndKeyMapping.put(thisVersionName, thisRSAPrivateKey);
        }

    }

    private String encodeByCurrentVersionKey(String sourceString, String definedVersion) throws Exception {
        if ("".equals(definedVersion)) {
            throw new Exception("[ERROR]CANOT GET DATA SECURITY VERSION !");
        } else {
            byte[] result = null;
            byte[] currentRSAPrivateKey = (byte[]) this.versionAndKeyMapping.get(definedVersion);
            if (currentRSAPrivateKey == null) {
                throw new Exception("非法的版本号:" + definedVersion);
            } else {
                byte[] sourceData = sourceString.getBytes("GBK");
                if (sourceData.length > 52) {
                    throw new Exception("[ERROR]Data longer than 53 bytes:" + sourceString);
                } else {
                    PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(currentRSAPrivateKey);
                    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                    PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
                    Cipher cipher = Cipher.getInstance("RSA");
                    cipher.init(1, privateKey);
                    result = cipher.doFinal(sourceData);
                    String resultEncodedString = (new BASE64Encoder()).encode(result);
                    resultEncodedString = resultEncodedString.trim();
                    resultEncodedString = resultEncodedString.replaceAll("\r\n", "<n>");
                    resultEncodedString = resultEncodedString.replaceAll("\n", "<n>");
                    resultEncodedString = resultEncodedString.replaceAll("\r", "<n>");
                    resultEncodedString = resultEncodedString.replaceAll("\r", "");
                    resultEncodedString = resultEncodedString.replaceAll("\t", "<t>");
                    return definedVersion + "!" + resultEncodedString;
                }
            }
        }
    }
}

