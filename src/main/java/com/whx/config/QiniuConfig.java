package com.whx.config;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Component
@Data
@ConfigurationProperties(prefix = "oss.qiniu")
public class QiniuConfig {

    private String accessKey;

    private String secretKey;

    private Long expiredSeconds;

    /**
     * 文件存储空间名
     */
    private String bucketName;

    /**
     * 文件外链
     */
    private String urlPre;

    private UploadManager uploadManager;
    private BucketManager bucketManager;
    private Configuration configuration;
    private Client client;
    //密钥配置
    private Auth auth;

    public Client getClient(){
        if (client==null){
            client=new Client(getConfiguration());
        }
        return client;
    }

    public BucketManager getBucketManager(){
        if (bucketManager==null){
            bucketManager=new BucketManager(getAuth(),getConfiguration());
        }
        return bucketManager;
    }

    public UploadManager getUploadManager(){
        if(uploadManager==null){
            uploadManager=new UploadManager(getConfiguration());
        }
        return uploadManager;
    }

    public Configuration getConfiguration(){
        if (configuration==null) {
            Region region = Region.autoRegion();
            configuration=new Configuration(region);
        }
        return configuration;
    }

    public Auth getAuth(){
        if(auth==null){
            auth=Auth.create(getAccessKey(),getSecretKey());
        }
        return auth;
    }

    /**
     * 简单上传模式的凭证
     * @return token
     */
    public String getUpToken(){
        return getAuth().uploadToken(getBucketName());
    }

    /**
     * 覆盖上传模式的凭证
     * @param fileKey 文件键
     * @return token
     */
    public String getUpToken(String fileKey){
        return getAuth().uploadToken(getBucketName(),fileKey);
    }

    /**
     * 将本地文件上传
     * @param filePath 本地文件路径
     * @param fileKey 上传七牛云后保存的文件路径名称
     * @return 上传结果
     */
    public String upload(String filePath,String fileKey){
        Response res;
        try{
            res=getUploadManager().put(filePath,fileKey,getUpToken(fileKey));
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);
            return urlPre+"/"+putRet.key;
        } catch (QiniuException e) {
            e.printStackTrace();
            return "上传失败";
        }
    }

    /**
     * 上传二进制数据
     * @param data 二进制数据
     * @param fileKey 上传七牛云后保存的文件路径名称
     * @return 上传结果
     */
    public String upload(byte[] data,String fileKey){
        Response res;
        try{
            res=getUploadManager().put(data,fileKey,getUpToken(fileKey));
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);
            return urlPre+"/"+putRet.key;
        } catch (QiniuException e) {
            e.printStackTrace();
            return "上传失败";
        }
    }

    /**
     * 上传输入流
     * @param inputStream 输入流
     * @param fileKey 上传七牛云后保存的文件路径名称
     * @return 上传结果
     */
    public String upload(InputStream inputStream,String fileKey){
        Response res;
        try{
            res=getUploadManager().put(inputStream,fileKey,getUpToken(fileKey),null,null);
            DefaultPutRet putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);
            return urlPre+"/"+putRet.key;
        } catch (QiniuException e) {
            e.printStackTrace();
            return "上传失败";
        }
    }

    /**
     * 删除文件
     * @param fileKey 上传七牛云后保存的文件路径名称
     * @return 删除结果
     * @throws QiniuException 异常
     */
    public boolean delete(String fileKey) throws QiniuException {
        Response response = getBucketManager().delete(this.getBucketName(), fileKey);
        return response.statusCode == 200;
    }

    /**
     * 获取公共空间文件
     * @param fileKey 上传七牛云后保存的文件路径名称
     * @return url链接
     * @throws UnsupportedEncodingException 异常
     */
    public String getFile(String fileKey) throws UnsupportedEncodingException {
        String encodedFileName = URLEncoder.encode(fileKey, "utf-8").replace("+", "%20");
        return String.format("%s/%s", urlPre, encodedFileName);
    }

    /**
     * 获取私有空间文件
     * @param fileKey 上传七牛云后保存的文件路径名称
     * @return url链接
     * @throws UnsupportedEncodingException 异常
     */
    public String getPrivateFile(String fileKey) throws UnsupportedEncodingException {
        String encodedFileName = URLEncoder.encode(fileKey, "utf-8").replace("+", "%20");
        String url = String.format("%s/%s", urlPre, encodedFileName);
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.privateDownloadUrl(url, expiredSeconds);
    }
}
