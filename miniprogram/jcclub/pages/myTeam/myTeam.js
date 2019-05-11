//获取应用实例
const app = getApp()

// pages/team/team.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    teams: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.request({
      url: app.globalData.requestUri + '/personal?actionName=myTeam&userInfoId=' + app.globalData.userId,
      success: function (res) {
        that.setData({
          teams: res.data.data
        })
      }
    })
  },
  publish: function (event) {
    var that = this;
    console.log(event.currentTarget.dataset)
    wx.request({
      url: app.globalData.requestUri + '/personal?actionName=publishMyPost&teamId=' + event.currentTarget.dataset.teamid
        + '&isPublish=' + event.currentTarget.dataset.ispublish,
      success: function (res) {
        if (res.data.resCode == '200') {
            wx.showToast({
              title: '更新成功',
              icon: 'succes',
              duration: 1000,
              mask: true
            })
            that.onLoad();
          } 
      }
    })
  }
})