//获取应用实例
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    talents: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.request({
      url: app.globalData.requestUri + '/personal?actionName=otherDelivery&userInfoId=' + app.globalData.userId,
      success: function (res) {
        that.setData({
          talents: res.data.data
        })
      }
    })
  },
  changePartner:function(event){
    console.log(event)
    var that = this;
    wx.request({
      url: app.globalData.requestUri + '/personal?actionName=addPartner&userInfoId=' + app.globalData.userId
        + '&talentId=' + event.target.dataset.talentid + '&isPartner=' + event.target.dataset.ispartner,
      success: function (res) {
        wx.showToast({
          title: '变更成功',
          icon: 'succes',
          duration: 2000,
          mask: true
        })
        that.onLoad();
      }
    })
  }
})