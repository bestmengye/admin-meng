package com.meng.core.social.qq.api;

/**
 * @author mengye
 * @desc QQ的用户信息
 * @date 2021/1/28 16:22
 */
public class QQUserInfo {

    /**
     * 返回码
     */
    private String ret;

    /**
     * openId
     */
    private String openId;

    /**
     * 返回信息
     */
    private String msg;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 大小为30×30像素的QQ空间头像URL。
     * figureurl	    大小为30×30像素的QQ空间头像URL。
     * figureurl_1	    大小为50×50像素的QQ空间头像URL。
     * figureurl_2	    大小为100×100像素的QQ空间头像URL。
     * figureurl_qq_1	大小为40×40像素的QQ头像URL。
     * figureurl_qq_2	大小为100×100像素的QQ头像URL。需要注意，不是所有的用户都拥有QQ的100x100的头像，但40x40像素则是一定会有。
     */
    private String figureurl;
    private String figureurl_1;
    private String figureurl_2;
    private String figureurl_qq_1;
    private String figureurl_qq_2;

    /**
     * 性别  如果获取不到则默认返回"男"
     */
    private String gender;
    /**
     * 是否是黄钻
     */
    private String is_yellow_vip;
    /**
     * 是否是vip
     */
    private String vip;
    /**
     * 黄钻等级
     */
    private String yellow_vip_level;
    /**
     * QQ等级
     */
    private String level;

    /**
     * 是否是黄钻年费VIP
     */
    private String is_yellow_year_vip;

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFigureurl() {
        return figureurl;
    }

    public void setFigureurl(String figureurl) {
        this.figureurl = figureurl;
    }

    public String getFigureurl_1() {
        return figureurl_1;
    }

    public void setFigureurl_1(String figureurl_1) {
        this.figureurl_1 = figureurl_1;
    }

    public String getFigureurl_2() {
        return figureurl_2;
    }

    public void setFigureurl_2(String figureurl_2) {
        this.figureurl_2 = figureurl_2;
    }

    public String getFigureurl_qq_1() {
        return figureurl_qq_1;
    }

    public void setFigureurl_qq_1(String figureurl_qq_1) {
        this.figureurl_qq_1 = figureurl_qq_1;
    }

    public String getFigureurl_qq_2() {
        return figureurl_qq_2;
    }

    public void setFigureurl_qq_2(String figureurl_qq_2) {
        this.figureurl_qq_2 = figureurl_qq_2;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIs_yellow_vip() {
        return is_yellow_vip;
    }

    public void setIs_yellow_vip(String is_yellow_vip) {
        this.is_yellow_vip = is_yellow_vip;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getYellow_vip_level() {
        return yellow_vip_level;
    }

    public void setYellow_vip_level(String yellow_vip_level) {
        this.yellow_vip_level = yellow_vip_level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIs_yellow_year_vip() {
        return is_yellow_year_vip;
    }

    public void setIs_yellow_year_vip(String is_yellow_year_vip) {
        this.is_yellow_year_vip = is_yellow_year_vip;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

}
