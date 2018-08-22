package com.lyl.mvptest.beans;

import java.util.List;

/**
 * create 2018/8/21
 * author lyl
 */
public class HotMovieinfo {
    @Override
    public String toString() {
        return "HotMovieinfo{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", title='" + title + '\'' +
                ", subjects=" + subjects +
                '}';
    }

    /**
     * count : 20
     * start : 0
     * total : 36
     * subjects : [{"rating":{"max":10,"average":7.3,"stars":"40","min":0},"genres":["剧情","喜剧"],"title":"一出好戏","casts":[{"alt":"https://movie.douban.com/celebrity/1274242/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp"},"name":"黄渤","id":"1274242"},{"alt":"https://movie.douban.com/celebrity/1138320/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1268.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1268.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1268.webp"},"name":"舒淇","id":"1138320"},{"alt":"https://movie.douban.com/celebrity/1274388/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1356403251.95.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1356403251.95.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1356403251.95.webp"},"name":"王宝强","id":"1274388"}],"collect_count":416458,"original_title":"一出好戏","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1274242/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp"},"name":"黄渤","id":"1274242"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2529571873.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2529571873.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2529571873.webp"},"alt":"https://movie.douban.com/subject/26985127/","id":"26985127"},{"rating":{"max":10,"average":0,"stars":"00","min":0},"genres":["剧情","儿童","奇幻"],"title":"疯狂暑期之哈喽怪物","casts":[{"alt":"https://movie.douban.com/celebrity/1398769/","avatars":{"small":"https://img1.doubanio.com/f/movie/ca527386eb8c4e325611e22dfcb04cc116d6b423/pics/movie/celebrity-default-small.png","large":"https://img3.doubanio.com/f/movie/63acc16ca6309ef191f0378faf793d1096a3e606/pics/movie/celebrity-default-large.png","medium":"https://img1.doubanio.com/f/movie/8dd0c794499fe925ae2ae89ee30cd225750457b4/pics/movie/celebrity-default-medium.png"},"name":"许皓勋","id":"1398769"},{"alt":"https://movie.douban.com/celebrity/1352384/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1444968275.47.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1444968275.47.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1444968275.47.webp"},"name":"李亚真","id":"1352384"},{"alt":"https://movie.douban.com/celebrity/1108968/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1208.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1208.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1208.webp"},"name":"罗家英","id":"1108968"}],"collect_count":15,"original_title":"疯狂暑期之哈喽怪物","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1383874/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1534159179.22.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1534159179.22.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1534159179.22.webp"},"name":"史震飞","id":"1383874"}],"year":"2018","images":{"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2530152518.webp","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2530152518.webp","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2530152518.webp"},"alt":"https://movie.douban.com/subject/30291398/","id":"30291398"},{"rating":{"max":10,"average":7,"stars":"35","min":0},"genres":["喜剧","动画","奇幻"],"title":"精灵旅社3：疯狂假期","casts":[{"alt":"https://movie.douban.com/celebrity/1054523/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p28807.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p28807.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p28807.webp"},"name":"亚当·桑德勒","id":"1054523"},{"alt":"https://movie.douban.com/celebrity/1022649/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p36102.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p36102.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p36102.webp"},"name":"凯瑟琳·哈恩","id":"1022649"},{"alt":"https://movie.douban.com/celebrity/1054409/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1091.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1091.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1091.webp"},"name":"史蒂夫·布西密","id":"1054409"}],"collect_count":35198,"original_title":"Hotel Transylvania 3: Summer Vacation","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1016727/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p24281.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p24281.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p24281.webp"},"name":"格恩迪·塔塔科夫斯基","id":"1016727"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530591543.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530591543.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530591543.webp"},"alt":"https://movie.douban.com/subject/26630714/","id":"26630714"},{"rating":{"max":10,"average":6.1,"stars":"30","min":0},"genres":["动作","科幻","惊悚"],"title":"巨齿鲨","casts":[{"alt":"https://movie.douban.com/celebrity/1049484/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p424.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p424.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p424.webp"},"name":"杰森·斯坦森","id":"1049484"},{"alt":"https://movie.douban.com/celebrity/1040990/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p37168.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p37168.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p37168.webp"},"name":"李冰冰","id":"1040990"},{"alt":"https://movie.douban.com/celebrity/1004593/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p9747.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p9747.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p9747.webp"},"name":"雷恩·威尔森","id":"1004593"}],"collect_count":83903,"original_title":"The Meg","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1022710/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1379831737.28.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1379831737.28.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1379831737.28.webp"},"name":"乔·德特杜巴","id":"1022710"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530572643.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530572643.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530572643.webp"},"alt":"https://movie.douban.com/subject/26426194/","id":"26426194"},{"rating":{"max":10,"average":7.1,"stars":"35","min":0},"genres":["喜剧","奇幻"],"title":"快把我哥带走","casts":[{"alt":"https://movie.douban.com/celebrity/1274254/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1465826349.1.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1465826349.1.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1465826349.1.webp"},"name":"张子枫","id":"1274254"},{"alt":"https://movie.douban.com/celebrity/1354775/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1523448357.59.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1523448357.59.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1523448357.59.webp"},"name":"彭昱畅","id":"1354775"},{"alt":"https://movie.douban.com/celebrity/1337036/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1450954931.7.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1450954931.7.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1450954931.7.webp"},"name":"赵今麦","id":"1337036"}],"collect_count":35129,"original_title":"快把我哥带走","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1276077/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13508.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13508.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p13508.webp"},"name":"郑芬芬","id":"1276077"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2531080870.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2531080870.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2531080870.webp"},"alt":"https://movie.douban.com/subject/30122633/","id":"30122633"},{"rating":{"max":10,"average":6.7,"stars":"35","min":0},"genres":["喜剧"],"title":"西虹市首富","casts":[{"alt":"https://movie.douban.com/celebrity/1325700/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1533527370.41.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1533527370.41.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1533527370.41.webp"},"name":"沈腾","id":"1325700"},{"alt":"https://movie.douban.com/celebrity/1341903/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1446281965.79.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1446281965.79.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1446281965.79.webp"},"name":"宋芸桦","id":"1341903"},{"alt":"https://movie.douban.com/celebrity/1322777/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1413261818.41.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1413261818.41.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1413261818.41.webp"},"name":"张一鸣","id":"1322777"}],"collect_count":527743,"original_title":"西虹市首富","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1350410/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1437030925.47.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1437030925.47.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1437030925.47.webp"},"name":"闫非","id":"1350410"},{"alt":"https://movie.douban.com/celebrity/1350409/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1437031053.5.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1437031053.5.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1437031053.5.webp"},"name":"彭大魔","id":"1350409"}],"year":"2018","images":{"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2529206747.webp","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2529206747.webp","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2529206747.webp"},"alt":"https://movie.douban.com/subject/27605698/","id":"27605698"},{"rating":{"max":10,"average":3.7,"stars":"20","min":0},"genres":["喜剧","动作","爱情"],"title":"欧洲攻略","casts":[{"alt":"https://movie.douban.com/celebrity/1115918/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p33525.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p33525.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p33525.webp"},"name":"梁朝伟","id":"1115918"},{"alt":"https://movie.douban.com/celebrity/1337000/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1401722517.74.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1401722517.74.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1401722517.74.webp"},"name":"吴亦凡","id":"1337000"},{"alt":"https://movie.douban.com/celebrity/1274479/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p48044.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p48044.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p48044.webp"},"name":"唐嫣","id":"1274479"}],"collect_count":30030,"original_title":"欧洲攻略","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1280599/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17804.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17804.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p17804.webp"},"name":"马楚成","id":"1280599"}],"year":"2018","images":{"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2529410377.webp","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2529410377.webp","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2529410377.webp"},"alt":"https://movie.douban.com/subject/26351812/","id":"26351812"},{"rating":{"max":10,"average":3.7,"stars":"20","min":0},"genres":["剧情","喜剧","动作"],"title":"新乌龙院之笑闹江湖","casts":[{"alt":"https://movie.douban.com/celebrity/1329167/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1369131268.31.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1369131268.31.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1369131268.31.webp"},"name":"王宁","id":"1329167"},{"alt":"https://movie.douban.com/celebrity/1339768/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1521080021.74.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1521080021.74.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1521080021.74.webp"},"name":"孔连顺","id":"1339768"},{"alt":"https://movie.douban.com/celebrity/1321587/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1443582361.31.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1443582361.31.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1443582361.31.webp"},"name":"王智","id":"1321587"}],"collect_count":3701,"original_title":"新乌龙院之笑闹江湖","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1275412/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p11221.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p11221.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p11221.webp"},"name":"朱延平","id":"1275412"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2529762680.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2529762680.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2529762680.webp"},"alt":"https://movie.douban.com/subject/26309969/","id":"26309969"},{"rating":{"max":10,"average":8.7,"stars":"45","min":0},"genres":["剧情","犯罪","家庭"],"title":"小偷家族","casts":[{"alt":"https://movie.douban.com/celebrity/1042693/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1534263369.18.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1534263369.18.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1534263369.18.webp"},"name":"中川雅也","id":"1042693"},{"alt":"https://movie.douban.com/celebrity/1274350/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1442220877.34.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1442220877.34.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1442220877.34.webp"},"name":"安藤樱","id":"1274350"},{"alt":"https://movie.douban.com/celebrity/1320978/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1428591465.76.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1428591465.76.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1428591465.76.webp"},"name":"松冈茉优","id":"1320978"}],"collect_count":211045,"original_title":"万引き家族","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1274351/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1363134033.35.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1363134033.35.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1363134033.35.webp"},"name":"是枝裕和","id":"1274351"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530599636.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530599636.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530599636.webp"},"alt":"https://movie.douban.com/subject/27622447/","id":"27622447"},{"rating":{"max":10,"average":7.6,"stars":"40","min":0},"genres":["纪录片"],"title":"大三儿","casts":[{"alt":"https://movie.douban.com/celebrity/1399300/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1534672503.13.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1534672503.13.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1534672503.13.webp"},"name":"叶云","id":"1399300"}],"collect_count":4321,"original_title":"大三儿","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1331818/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1524120551.67.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1524120551.67.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1524120551.67.webp"},"name":"佟晟嘉","id":"1331818"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530569532.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530569532.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530569532.webp"},"alt":"https://movie.douban.com/subject/27119292/","id":"27119292"},{"rating":{"max":10,"average":2.8,"stars":"15","min":0},"genres":["喜剧","冒险"],"title":"爱情公寓","casts":[{"alt":"https://movie.douban.com/celebrity/1313841/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1420296836.46.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1420296836.46.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1420296836.46.webp"},"name":"陈赫","id":"1313841"},{"alt":"https://movie.douban.com/celebrity/1275443/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34035.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34035.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p34035.webp"},"name":"袁弘","id":"1275443"},{"alt":"https://movie.douban.com/celebrity/1313784/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490251985.48.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490251985.48.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1490251985.48.webp"},"name":"娄艺潇","id":"1313784"}],"collect_count":199404,"original_title":"爱情公寓","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1313918/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1526270943.26.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1526270943.26.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1526270943.26.webp"},"name":"韦正","id":"1313918"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2521648155.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2521648155.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2521648155.webp"},"alt":"https://movie.douban.com/subject/24852545/","id":"24852545"},{"rating":{"max":10,"average":9,"stars":"45","min":0},"genres":["剧情","喜剧"],"title":"我不是药神","casts":[{"alt":"https://movie.douban.com/celebrity/1274297/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p43738.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p43738.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p43738.webp"},"name":"徐峥","id":"1274297"},{"alt":"https://movie.douban.com/celebrity/1313837/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1496577458.38.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1496577458.38.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1496577458.38.webp"},"name":"王传君","id":"1313837"},{"alt":"https://movie.douban.com/celebrity/1276085/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1514533436.1.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1514533436.1.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1514533436.1.webp"},"name":"周一围","id":"1276085"}],"collect_count":1309856,"original_title":"我不是药神","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1349765/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1529658740.26.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1529658740.26.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1529658740.26.webp"},"name":"文牧野","id":"1349765"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2519070834.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2519070834.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2519070834.webp"},"alt":"https://movie.douban.com/subject/26752088/","id":"26752088"},{"rating":{"max":10,"average":5.4,"stars":"30","min":0},"genres":["动画"],"title":"神秘世界历险记4","casts":[{"alt":"https://movie.douban.com/celebrity/1395143/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1528456334.7.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1528456334.7.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1528456334.7.webp"},"name":"阎么么","id":"1395143"},{"alt":"https://movie.douban.com/celebrity/1392959/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1531717367.71.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1531717367.71.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1531717367.71.webp"},"name":"赵一博","id":"1392959"},{"alt":"https://movie.douban.com/celebrity/1340809/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1526278703.48.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1526278703.48.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1526278703.48.webp"},"name":"张磊","id":"1340809"}],"collect_count":3471,"original_title":"神秘世界历险记4","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1321732/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1436771114.49.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1436771114.49.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1436771114.49.webp"},"name":"王云飞","id":"1321732"},{"alt":"https://movie.douban.com/celebrity/1360428/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1526278577.39.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1526278577.39.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1526278577.39.webp"},"name":"张林旭","id":"1360428"},{"alt":"https://movie.douban.com/celebrity/1337468/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1435053152.21.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1435053152.21.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1435053152.21.webp"},"name":"李佳怡","id":"1337468"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2528380655.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2528380655.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2528380655.webp"},"alt":"https://movie.douban.com/subject/30208005/","id":"30208005"},{"rating":{"max":10,"average":6.4,"stars":"35","min":0},"genres":["纪录片"],"title":"最后的棒棒","casts":[{"alt":"https://movie.douban.com/celebrity/1396140/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1529657375.78.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1529657375.78.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1529657375.78.webp"},"name":"何苦","id":"1396140"}],"collect_count":2530,"original_title":"最后的棒棒","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1396140/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1529657375.78.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1529657375.78.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1529657375.78.webp"},"name":"何苦","id":"1396140"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2528959166.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2528959166.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2528959166.webp"},"alt":"https://movie.douban.com/subject/30254589/","id":"30254589"},{"rating":{"max":10,"average":0,"stars":"00","min":0},"genres":["动作","动画","冒险"],"title":"美食大冒险之英雄烩","casts":[{"alt":"https://movie.douban.com/celebrity/1399289/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1534416134.4.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1534416134.4.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1534416134.4.webp"},"name":"杨光普照","id":"1399289"},{"alt":"https://movie.douban.com/celebrity/1399290/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1534416150.87.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1534416150.87.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1534416150.87.webp"},"name":"孟宇","id":"1399290"},{"alt":"https://movie.douban.com/celebrity/1389748/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520590413.89.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520590413.89.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1520590413.89.webp"},"name":"张喆","id":"1389748"}],"collect_count":1637,"original_title":"美食大冒险之英雄烩","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1389867/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pnSS497gRZs0cel_avatar_uploaded1520596328.84.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pnSS497gRZs0cel_avatar_uploaded1520596328.84.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/pnSS497gRZs0cel_avatar_uploaded1520596328.84.webp"},"name":"孙海鹏","id":"1389867"}],"year":"2018","images":{"small":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2530385279.webp","large":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2530385279.webp","medium":"https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2530385279.webp"},"alt":"https://movie.douban.com/subject/26290398/","id":"26290398"},{"rating":{"max":10,"average":7,"stars":"35","min":0},"genres":["动画","奇幻"],"title":"风语咒","casts":[{"alt":"https://movie.douban.com/celebrity/1329887/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1370588849.4.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1370588849.4.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1370588849.4.webp"},"name":"路知行","id":"1329887"},{"alt":"https://movie.douban.com/celebrity/1340811/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1478049229.21.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1478049229.21.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1478049229.21.webp"},"name":"阎萌萌","id":"1340811"},{"alt":"https://movie.douban.com/celebrity/1390805/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1522323624.45.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1522323624.45.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1522323624.45.webp"},"name":"褚珺","id":"1390805"}],"collect_count":33543,"original_title":"风语咒","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1364166/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1501141090.9.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1501141090.9.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1501141090.9.webp"},"name":"刘阔","id":"1364166"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530872223.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530872223.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530872223.webp"},"alt":"https://movie.douban.com/subject/30146756/","id":"30146756"},{"rating":{"max":10,"average":6.5,"stars":"35","min":0},"genres":["动作","悬疑","古装"],"title":"狄仁杰之四大天王","casts":[{"alt":"https://movie.douban.com/celebrity/1274608/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p49483.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p49483.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p49483.webp"},"name":"赵又廷","id":"1274608"},{"alt":"https://movie.douban.com/celebrity/1275721/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p36925.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p36925.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p36925.webp"},"name":"冯绍峰","id":"1275721"},{"alt":"https://movie.douban.com/celebrity/1314535/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1399987210.67.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1399987210.67.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1399987210.67.webp"},"name":"林更新","id":"1314535"}],"collect_count":136797,"original_title":"狄仁杰之四大天王","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1007152/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1393840734.39.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1393840734.39.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1393840734.39.webp"},"name":"徐克","id":"1007152"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2526405034.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2526405034.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2526405034.webp"},"alt":"https://movie.douban.com/subject/25882296/","id":"25882296"},{"rating":{"max":10,"average":0,"stars":"00","min":0},"genres":["剧情","传记"],"title":"李保国","casts":[{"alt":"https://movie.douban.com/celebrity/1274650/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5995.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5995.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p5995.webp"},"name":"林永健","id":"1274650"},{"alt":"https://movie.douban.com/celebrity/1034637/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1415028031.49.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1415028031.49.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1415028031.49.webp"},"name":"颜丹晨","id":"1034637"},{"alt":"https://movie.douban.com/celebrity/1301518/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p14604.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p14604.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p14604.webp"},"name":"尤勇","id":"1301518"}],"collect_count":431,"original_title":"李保国","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1396637/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/pTFquCxc_hGYcel_avatar_uploaded1530590933.7.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/pTFquCxc_hGYcel_avatar_uploaded1530590933.7.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/pTFquCxc_hGYcel_avatar_uploaded1530590933.7.webp"},"name":"赵琦","id":"1396637"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530569123.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530569123.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2530569123.webp"},"alt":"https://movie.douban.com/subject/27104330/","id":"27104330"},{"rating":{"max":10,"average":5,"stars":"25","min":0},"genres":["儿童","动画","冒险"],"title":"新大头儿子和小头爸爸3：俄罗斯奇遇记","casts":[{"alt":"https://movie.douban.com/celebrity/1318433/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44177.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44177.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44177.webp"},"name":"刘纯燕","id":"1318433"},{"alt":"https://movie.douban.com/celebrity/1318435/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44180.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44180.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p44180.webp"},"name":"董浩","id":"1318435"},{"alt":"https://movie.douban.com/celebrity/1274251/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1494778054.15.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1494778054.15.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1494778054.15.webp"},"name":"鞠萍","id":"1274251"}],"collect_count":7429,"original_title":"新大头儿子和小头爸爸3：俄罗斯奇遇记","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1342907/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1469705072.9.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1469705072.9.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1469705072.9.webp"},"name":"何澄","id":"1342907"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2522820714.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2522820714.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2522820714.webp"},"alt":"https://movie.douban.com/subject/30198729/","id":"30198729"},{"rating":{"max":10,"average":7.4,"stars":"40","min":0},"genres":["喜剧","爱情","歌舞"],"title":"妈妈咪呀2","casts":[{"alt":"https://movie.douban.com/celebrity/1318674/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1426508419.1.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1426508419.1.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1426508419.1.webp"},"name":"莉莉·詹姆斯","id":"1318674"},{"alt":"https://movie.douban.com/celebrity/1053586/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p4902.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p4902.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p4902.webp"},"name":"阿曼达·塞弗里德","id":"1053586"},{"alt":"https://movie.douban.com/celebrity/1031902/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1484993245.89.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1484993245.89.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1484993245.89.webp"},"name":"朱丽·沃特斯","id":"1031902"}],"collect_count":7887,"original_title":"Mamma Mia! Here We Go Again","subtype":"movie","directors":[{"alt":"https://movie.douban.com/celebrity/1319911/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p48233.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p48233.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p48233.webp"},"name":"欧·帕克","id":"1319911"}],"year":"2018","images":{"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2528272481.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2528272481.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2528272481.webp"},"alt":"https://movie.douban.com/subject/27050259/","id":"27050259"}]
     * title : 正在上映的电影-北京
     */

    private int count;
    private int start;
    private int total;
    private String title;
    private List<SubjectsBean> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsBean> subjects) {
        this.subjects = subjects;
    }

    public static class SubjectsBean {
        @Override
        public String toString() {
            return "SubjectsBean{" +
                    "rating=" + rating +
                    ", title='" + title + '\'' +
                    ", collect_count=" + collect_count +
                    ", original_title='" + original_title + '\'' +
                    ", subtype='" + subtype + '\'' +
                    ", year='" + year + '\'' +
                    ", images=" + images +
                    ", alt='" + alt + '\'' +
                    ", id='" + id + '\'' +
                    ", genres=" + genres +
                    ", casts=" + casts +
                    ", directors=" + directors +
                    '}';
        }

        /**
         * rating : {"max":10,"average":7.3,"stars":"40","min":0}
         * genres : ["剧情","喜剧"]
         * title : 一出好戏
         * casts : [{"alt":"https://movie.douban.com/celebrity/1274242/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp"},"name":"黄渤","id":"1274242"},{"alt":"https://movie.douban.com/celebrity/1138320/","avatars":{"small":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1268.webp","large":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1268.webp","medium":"https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1268.webp"},"name":"舒淇","id":"1138320"},{"alt":"https://movie.douban.com/celebrity/1274388/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1356403251.95.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1356403251.95.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1356403251.95.webp"},"name":"王宝强","id":"1274388"}]
         * collect_count : 416458
         * original_title : 一出好戏
         * subtype : movie
         * directors : [{"alt":"https://movie.douban.com/celebrity/1274242/","avatars":{"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp"},"name":"黄渤","id":"1274242"}]
         * year : 2018
         * images : {"small":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2529571873.webp","large":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2529571873.webp","medium":"https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2529571873.webp"}
         * alt : https://movie.douban.com/subject/26985127/
         * id : 26985127
         */

        private RatingBean rating;
        private String title;
        private int collect_count;
        private String original_title;
        private String subtype;
        private String year;
        private ImagesBean images;
        private String alt;
        private String id;
        private List<String> genres;
        private List<CastsBean> casts;
        private List<DirectorsBean> directors;

        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
            this.rating = rating;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getCollect_count() {
            return collect_count;
        }

        public void setCollect_count(int collect_count) {
            this.collect_count = collect_count;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public void setOriginal_title(String original_title) {
            this.original_title = original_title;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public ImagesBean getImages() {
            return images;
        }

        public void setImages(ImagesBean images) {
            this.images = images;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public List<CastsBean> getCasts() {
            return casts;
        }

        public void setCasts(List<CastsBean> casts) {
            this.casts = casts;
        }

        public List<DirectorsBean> getDirectors() {
            return directors;
        }

        public void setDirectors(List<DirectorsBean> directors) {
            this.directors = directors;
        }

        public static class RatingBean {
            /**
             * max : 10
             * average : 7.3
             * stars : 40
             * min : 0
             */

            private int max;
            private double average;
            private String stars;
            private int min;

            public int getMax() {
                return max;
            }

            public void setMax(int max) {
                this.max = max;
            }

            public double getAverage() {
                return average;
            }

            public void setAverage(double average) {
                this.average = average;
            }

            public String getStars() {
                return stars;
            }

            public void setStars(String stars) {
                this.stars = stars;
            }

            public int getMin() {
                return min;
            }

            public void setMin(int min) {
                this.min = min;
            }
        }

        public static class ImagesBean {
            /**
             * small : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2529571873.webp
             * large : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2529571873.webp
             * medium : https://img3.doubanio.com/view/photo/s_ratio_poster/public/p2529571873.webp
             */

            private String small;
            private String large;
            private String medium;

            public String getSmall() {
                return small;
            }

            public void setSmall(String small) {
                this.small = small;
            }

            public String getLarge() {
                return large;
            }

            public void setLarge(String large) {
                this.large = large;
            }

            public String getMedium() {
                return medium;
            }

            public void setMedium(String medium) {
                this.medium = medium;
            }
        }

        public static class CastsBean {
            /**
             * alt : https://movie.douban.com/celebrity/1274242/
             * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp"}
             * name : 黄渤
             * id : 1274242
             */

            private String alt;
            private AvatarsBean avatars;
            private String name;
            private String id;

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public AvatarsBean getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBean avatars) {
                this.avatars = avatars;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBean {
                /**
                 * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp
                 * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp
                 * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp
                 */

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }

        public static class DirectorsBean {
            /**
             * alt : https://movie.douban.com/celebrity/1274242/
             * avatars : {"small":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp","large":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp","medium":"https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp"}
             * name : 黄渤
             * id : 1274242
             */

            private String alt;
            private AvatarsBeanX avatars;
            private String name;
            private String id;

            public String getAlt() {
                return alt;
            }

            public void setAlt(String alt) {
                this.alt = alt;
            }

            public AvatarsBeanX getAvatars() {
                return avatars;
            }

            public void setAvatars(AvatarsBeanX avatars) {
                this.avatars = avatars;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public static class AvatarsBeanX {
                /**
                 * small : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp
                 * large : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp
                 * medium : https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1656.webp
                 */

                private String small;
                private String large;
                private String medium;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }
            }
        }
    }
}
