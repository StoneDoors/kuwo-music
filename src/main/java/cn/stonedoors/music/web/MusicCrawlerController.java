package cn.stonedoors.music.web;

import cn.stonedoors.music.contants.BaseResult;
import cn.stonedoors.music.dto.MusicItem;
import cn.stonedoors.music.dto.ResponseDto;
import cn.stonedoors.music.utils.CrawlerUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname MusicCrawlerController
 * @Description  酷狗音乐
 * @Date 2020/10/21  14:41
 * @Created by 石昌鹏
 */
@RestController
@RequestMapping("/music")
public class MusicCrawlerController {
    /**
     * @Description  列表查询
     * @Date 2020/10/21 14:41
     * @Created by 石昌鹏
     */
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public BaseResult<Object> search(Integer pageNum,Integer pageSize,String content) throws IOException {
        BaseResult<Object> br=new BaseResult<>();

        if(pageNum==null){
            pageNum=1;
        }
        if(pageSize==null){
            pageSize=10;
        }
        if(StringUtils.isEmpty(content)){
            br.setCode(1000);
            br.setMsg("缺少查询条件");
            return br;
        }
        StringBuffer base_url=new StringBuffer("http://www.kuwo.cn/api/www/search/searchMusicBykeyWord?");
        base_url.append("key="+ URLEncoder.encode(content));
        base_url.append("&pn="+pageNum);
        base_url.append("&rn="+pageSize);
        base_url.append("&httpsStatus=1");
        base_url.append("&reqId=cc337fa0-e856-11ea-8e2d-ab61b365fb50");
        Map<String,String> header=new HashMap<>();
        header.put("Cookie","_ga=GA1.2.1083049585.1590317697; _gid=GA1.2.2053211683.1598526974; _gat=1; Hm_lvt_cdb524f42f0ce19b169a8071123a4797=1597491567,1598094297,1598096480,1598526974; Hm_lpvt_cdb524f42f0ce19b169a8071123a4797=1598526974; kw_token=HYZQI4KPK3P");
        header.put("Referer","http://www.kuwo.cn/search/list?key=%E5%91%A8%E6%9D%B0%E4%BC%A6");
        header.put("csrf","HYZQI4KPK3P");
        String result = CrawlerUtils.sendGet(base_url.toString(), header);
        ResponseDto responseDto = JSONObject.parseObject(result, ResponseDto.class);
        if(responseDto.getCode()!=200){
            br.setCode(responseDto.getCode());
            br.setMsg("网站出错了");
            return br;
        }
        if(responseDto.getData()!=null){
            if(!CollectionUtils.isEmpty(responseDto.getData().getList())){
                //查找下载地址
                for(MusicItem item:responseDto.getData().getList()){
                    item.setDownUrl(ridToUrl(item.getRid()));
                    item.setPlayFlag("2");
                }
            }
            br.setData(responseDto.getData());
        }
        return br;
    }

    private String ridToUrl(String rid) throws IOException {
        String url="";
        if(!StringUtils.isEmpty(rid)){
            String api_music = "http://www.kuwo.cn/url?format=mp3&rid="+rid+"&response=url&type=convert_url3"
                    +"&br=128kmp3&from=web&t=1598528574799&httpsStatus=1"
                    +"&reqId=72259df1-e85a-11ea-a367-b5a64c5660e5";
            String s = CrawlerUtils.sendGet(api_music, null);
            JSONObject jsonObject = JSONObject.parseObject(s);
            url= jsonObject.getString("url");
        }
        return url;
    }
}
