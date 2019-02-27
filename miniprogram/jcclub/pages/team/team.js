//获取应用实例
const app = getApp()

// pages/team/team.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
      teams:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      wx.request({
          url: app.globalData.requestUri + '/teamLibrary?actionName=list',
          success: function (res) {
              that.setData({
                  teams:res.data.data
              })
          }
      })
  },

  toPublish: function(){
    wx.navigateTo({
      url: '/pages/teamInfo/teamInfo'
    })
  },

  delivery: function (event) {
    console.log(event.currentTarget.dataset)
    wx.request({
      url: app.globalData.requestUri + '/teamLibrary?actionName=delivery&userInfoId=' + app.globalData.userId + '&teamId=' + event.currentTarget.dataset.teamid,
      success: function (res) {
        if (res.data.resCode == '200') {
          wx.showToast({
            title: '保存成功',
            icon: 'succes',
            duration: 1000,
            mask: true
          })
        }
      }
    })
  },

})