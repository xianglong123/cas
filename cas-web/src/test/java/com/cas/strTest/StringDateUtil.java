package com.cas.strTest;

/**
 * @author: xianglong[1391086179@qq.com]
 * @date: 20:10 2020-04-07
 * @version: V1.0
 * @review:
 */
public class StringDateUtil {

    public static String[] getStrings() {
        return new String[] {
                "枣庄市市中区青檀路188号新昌批发市场一楼中区",
                "江苏省-无锡市-江阴市-临港街道钱江路10号2303室",
                "广东省-广州市-天河区-莲溪东路三巷1号101房",
                "广东省-深圳市-罗湖区-广东省深圳市龙华区腾龙路与中梅路交叉口东北角99号",
                "陕西省-西安市-其它区-2-1-1-1号商铺",
                "河北省-邢台市-其它区-01号",
                "江西省-赣州市-宁都县-江西省 赣州市 宁都县 梅江镇中山路1",
                "河北省-保定市-唐县-向阳街电影公司门市",
                "河北省-唐山市-遵化市-河北省唐山市遵化市遵兴公路西侧（红运房地产开发有限公司楼下）",
                "山西省-大同市-广灵县-山西大同市广灵县新一区市场11号",
                "河北省-邢台市-其它区-01号",
                "山西省-大同市-天镇县-平城区友谊南街光熙苑4楼3号商铺",
                "安徽省-亳州市-利辛县-利辛县程家集镇程集社区六队",
                "山西省-朔州市-怀仁县-怀仁市仁爱北路",
                "甘肃省-兰州市-城关区-科技街88号（神州数码城A12号）",
                "山西省-大同市-新荣区-平城区魏都大道云顶雅园底商589号",
                "江苏省-无锡市-其它区-金城东路18号1026-1-1-056",
                "上海-上海市-嘉定区-浙江省绍兴市诸暨市诸永高速诸暨服务区1",
                "上海-上海市-嘉定区-浙江省绍兴市诸暨市诸永高速诸暨服务区1",
                "上海-上海市-嘉定区-浙江省诸暨市浣东街道诸永高速诸暨服务区（西）",
                "山西省-大同市-其它区-瑞兴花园24号底商",
                "湖南省-长沙市-岳麓区-咸嘉湖街道金星中路428号步步高广场7层7011号",
                "湖南省-长沙市-望城区-月亮岛街道润和彩虹购物中心四层L410号门面",
                "新疆维吾尔自治区-喀什地区-英吉沙县-新疆喀什地区英吉沙县芒辛路",
                "江苏省-南京市-玄武区-长江路99号2201室",
                "湖南省-长沙市-其它区-27商铺",
                "江苏省-南京市-玄武区-长江路99号2201室",
                "广东省-惠州市-其它区-33号",
                "甘肃省-兰州市-七里河区-华坪街道办事处综合楼一楼139号",
                "河南省-南阳市-其它区-河南省南阳市邓州市湍河街道书香华府1号楼19号商铺",
                "湖南省-长沙市-芙蓉区-西街花园珠海桂山渔港6号",
                "江苏省-南京市-玄武区-长江路99号2201室",
                "安徽省-亳州市-其它区-京皖财富中心1栋118商铺",
                "安徽省-合肥市-蜀山区-潜山路190号银泰城4楼402-1",
                "江苏省-南京市-玄武区-长江路99号2201室",
                "内蒙古自治区-呼和浩特市-玉泉区-金峰市场新1号楼2-3商铺",
                "江苏省-常州市-武进区-湖塘镇延政中路16号B2008、B2009",
                "河南省-信阳市-潢川县-航空南路8号",
                "河南省-洛阳市-嵩县-闫庄镇滨河1号",
                "上海-上海市-奉贤区-新杨公路1800弄2幢3379室",
                "湖南省-长沙市-长沙县-湖南省茶陵县马江镇长联村民委员会",
                "广东省-肇庆市-高要区-金渡镇金晖街7号第一、二层【住改商】",
                "江苏省-南京市-玄武区-长江路99号2201室",
                "河南省-郑州市-惠济区-长兴路盛煌五环大厦4楼",
                "安徽省-合肥市-其它区-D-004号",
                "河南省-周口市-太康县-太康县马厂镇李麦村",
                "江苏省-南京市-玄武区-长江路99号2201室",
                "河南省-洛阳市-嵩县-嵩县城白云大厦楼下11号",
                "陕西省-西安市-未央区-长乐西苑3号",
                "陕西省-西安市-未央区-长乐西苑3号",
                "广东省-惠州市-其它区-七栋9号房屋一楼商铺之一卡",
                "新疆维吾尔自治区-哈密市-伊州区-新疆哈密市伊州区中山北路供销大厦一楼21号",
                "山西省-吕梁市-孝义市-山西省吕梁孝义市梧桐镇新区115号",
                "山西省-太原市-晋源区-山西省太原市晋源区晋源北街支行56号",
                "安徽省-亳州市-涡阳县-涡阳县高炉镇杨楼村蔡楼6号",
                "山东省-德州市-临邑县-临盘综合大市场26号",
                "河南省-郑州市-金水区-文化路60号附44号",
                "广东省-惠州市-其它区-陈江街道五一村日通达大楼一楼（惠州市日通达实业有限公司房屋）",
                "陕西省-西安市-阎良区-振兴街道办坡底村刘家沟组",
                "陕西省-渭南市-蒲城县-城关街道办事处西府村绿佳城菜市场西排第一家",
                "江苏省-苏州市-昆山市-千灯镇卫泾大街355号",
                "四川省-南充市-顺庆区-文化路250号",
                "江苏省-无锡市-滨湖区-苏州吴中经济开发区越溪街道天鹅荡路1-1号绿聚未来商业中心（ID PARK)购物中心4层H407号",
                "陕西省-西安市-莲湖区-丰镐西路70号",
                "重庆-重庆市-沙坪坝区-西林大道89号附58号",
                "甘肃省-兰州市-城关区-高新张苏滩800号2层205室",
                "江苏省-无锡市-滨湖区-苏州吴中经济开发区越溪街道天鹅荡路1-1号绿聚未来商业中心（ID PARK)购物中心4层H407号",
                "河南省-郑州市-郑州高新技术产业开发区-郑州高新区解放军信息工程大学综合服务楼212号",
                "陕西省-渭南市-其它区-仓程路吾悦广场负一楼",
                "上海-上海市-嘉定区-江桥镇金园一路766号8幢501室",
                "上海-上海市-浦东新区-金高路2062号",
                "江苏省-苏州市-其它区-平江路255号",
                "广西壮族自治区-南宁市-西乡塘区-广西南宁市北湖村安武大道北面3队",
                "上海-上海市-嘉定区-杭州市九堡家苑1",
                "广西壮族自治区-柳州市-其它区-东环大道２８６号兴佳·山水福第１４栋负一层１４号",
                "江苏省-南京市-玄武区-长江路99号2201室",
                "山东省-泰安市-其它区-沿街楼3号房",
                "江苏省-南京市-玄武区-长江路99号2201室",
                "江苏省-无锡市-其它区-团结中路3号（8-12-12-R）",
                "河南省-洛阳市-涧西区-涧西区景华路24号街坊2幢1-101",
                "贵州省-贵阳市-其它区-]",
                "河北省-廊坊市-三河市-河北省廊坊市三河市燕郊1",
                "广东省-东莞市-寮步镇-广东省东莞市东莞理工学院15号",
                "安徽省-六安市-金安区-文华路阳光欧洲城玫瑰苑52栋161、162号铺",
                "广西壮族自治区-南宁市-江南区-亭洪路48-1号江南万达广场C15栋155商铺",
                "四川省-成都市-金牛区-百寿路9号",
                "湖南省-长沙市-其它区-域",
                "重庆市-县-丰都县-丰都123号",
                "湖南省-长沙市-雨花区-圭塘街道万家丽中路三段36号喜盈门范城3栋1层3-111号商铺",
                "湖南省-长沙市-望城区-高塘岭街道联诚国际S-A3栋201-2",
                "江西省-九江市-彭泽县-山南新区龙翔大道龙翔.时代生活广场13栋第三层01号商铺",
                "四川省-成都市-成华区-成华区万科南街2号4栋1层101号门面",
                "湖南省-长沙市-雨花区-湖南省长沙市雨花区同升街道同超美食街A25号",
                "甘肃省-兰州市-其它区-20栋209",
                "山东省-泰安市-肥城市-山东省 泰安市 肥城市 龙山中路1",
                "贵州省-贵阳市-其它区-]",
                "山东省-枣庄市-市中区-市中区汇泉东路68号",
                "河南省-郑州市-金水区-红专路5号",
                "上海-上海市-杨浦区-国定路323号1001-105室",
                "陕西省-渭南市-韩城市-新城区龙门大街南段101号",
                "广东省-惠州市-惠阳区-惠阳区淡水承修一路5号",
                "四川省-成都市-成华区-四川武侯区锦城大道666号奥克斯广场",
                "北京-北京市-朝阳区-成寿寺路134号院1号楼15层1827",
                "湖南省-岳阳市-岳阳楼区-湖南省岳阳经济技术开发区金悦洋商业公园四栋120-123号",
                "河北省-保定市-莲池区-莲池大街150号",
                "广东省-深圳市-其它区-湖田路69号69-1",
                "江苏省-扬州市-江都区-仙女镇中远欧洲城中远路营业房122号",
                "四川省-达州市-大竹县-通源新城126",
                "江苏省-无锡市-其它区-金城东路18号1026-1-1-056",
                "河南省-安阳市-滑县-新区翰林苑南区B-48号商铺",
                "广东省-东莞市-南城街道-东莞市南城区金郎大厦首层2号铺",
                "重庆市-市辖区-九龙坡区-西彭镇127",
                "甘肃省-酒泉市-肃州区-甘肃省酒泉市肃州区北大街2号",
                "四川省-达州市-大竹县-双燕路128",
                "河南省-南阳市-其它区-河南省南阳市七里园乡七里园村四组",
                "河南省-南阳市-其它区-河南省南阳市邓州市湍河街道书香华府1号楼19号商铺",
                "广东省-深圳市-其它区-牛始埔聚英一街22号一楼",
                "江西省-上饶市-信州区-信州区龙芽亭20号1栋1-20号",
                "上海-上海市-宝山区-顾北东路365号C区523",
                "陕西省-西安市-新城区-长乐中路242号一层58号",
                "河北省-保定市-莲池区-金专路1-1号",
                "上海-上海市-浦东新区-三林路235号18幢193室",
                "重庆-重庆市-江北区-东环路59号负1-2星天广场3层3-5-8号",
                "山东省-泰安市-岱岳区-山东省泰安市长城路97号名仕尚座B栋住宅部分2单元706号",
                "河北省-保定市-莲池区-保定市莲池区金专路19号",
                "河北省-保定市-唐县-北罗镇朱北罗村",
                "江苏省-无锡市-江阴市-临港街道钱江路10号2303室",
                "重庆市-县-丰都县-丰都125",
                "四川省-绵阳市-江油市-九岭镇百和街3号",
                "江西省-九江市-德安县-财政局宿舍西侧-观湖豪庭1-12",
                "北京-北京市-平谷区-平谷镇新开街33号楼2层33-11-2",
                "江苏省-无锡市-江阴市-延陵路349-351号",
                "四川省-成都市-其它区-德全支路54号",
                "广东省-广州市-花都区-新华街新华路165宝田商业楼（部位：九楼901室）",
                "广东省-深圳市-其它区-5-3",
                "河南省-郑州市-金水区-经三路19号",
                "上海市-市辖区-浦东新区-青浦区金泽镇练西公路2850号1幢1层E区126室",
                "河南省-周口市-川汇区-周商路与莲花路交叉口向南200米路西",
                "安徽省-蚌埠市-禹会区-蚌埠市禹会区饮食街1-1号",
                "河南省-郑州市-惠济区-万客隆家具市场29厅金博士鞋柜",
                "安徽省-蚌埠市-蚌山区-蚌埠市蚌山区银泰城6楼1-2-3",
                "山东省-青岛市-崂山区-山东省青岛市崂山区鳌山卫街道办事处政府驻地对面1",
                "河南省-新乡市-凤泉区-锦园路北段路东",
                "安徽省-蚌埠市-禹会区-蚌埠市禹会区饮食街1-1号",
                "北京-北京市-朝阳区-清河营南街7号院4号楼3层301东区90号",
                "广西壮族自治区-贺州市-昭平县-平桂区公会镇粮所东面A地块F2-12号",
                "江苏省-南京市-玄武区-长江路99号2201室",
                "湖南省-长沙市-其它区-域。",
                "江苏省-南京市-玄武区-长江路99号2201室",
                "湖南省-长沙市-其它区-域",
                "广东省-东莞市-塘厦镇-广东省东莞市塘厦镇新明路76号4号楼202室",
                "江苏省-无锡市-其它区-金城东路18号1026-1-1-056",
                "湖南省-长沙市-其它区-域。",
                "江苏省-无锡市-其它区-金城东路18号1026-1-1-056",
                "湖南省-长沙市-其它区-域。",
                "河北省-邢台市-桥东区-新华路176号",
                "陕西省-西安市-其它区-凤城十二路66号29幢10129室",
                "重庆-重庆市-璧山县-重庆市璧山区璧泉街道11号",
                "江苏省-徐州市-鼓楼区-黄河北路12号",
                "河北省-邢台市-桥东区-新华路176号",
                "河南省-郑州市-巩义市-商务中心区锦里路10-17号",
                "河南省-濮阳市-其它区-河南省濮阳市黄河路与振兴路交叉口向北140号",
                "广西壮族自治区-南宁市-兴宁区-昆仑大道325号",
                "陕西省-西安市-其它区-曲江大道以西，曲江池北路以北汉华城甜心广场11号楼2单元12层整层",
                "江苏省-徐州市-铜山区-泉山庄北区A43号楼",
                "陕西省-西安市-其它区-雁南四路鸿基紫韵46-105",
                "甘肃省-兰州市-其它区-农贸市场商铺",
                "广东省-广州市-花都区-秀全街平步大道西45号之三",
                "北京-北京市-朝阳区-朝阳北路225号18层(团结湖孵化器0290号)",
                "江苏省-无锡市-惠山区-堰桥街道阳光壹佰光矩会所400-2216-2218",
                "湖南省-邵阳市-隆回县-花门街道横江社区方大桂花城45栋105号",
                "云南省-德宏傣族景颇族自治州-瑞丽市-姐岗路97号",
                "河北省-衡水市-其它区-金鸡大街西侧",
                "江苏省-无锡市-江阴市-临港街道钱江路10号2303室"
        };
    }

}
