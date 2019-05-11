//获取应用实例
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    teams: {}
  },
  onLoad: function(){
    var that = this;
    wx.request({
      url: app.globalData.requestUri + '/personal?actionName=queryPost',
      success: function (res) {
        that.setData({
          teams: res.data.data
        })
      }
    })
  },
  approval: function(event){
    console.log(event.currentTarget.dataset)
    var that = this;
    var postId = event.currentTarget.dataset.postid;
    var approvalStatus = event.currentTarget.dataset.approvalstatus;
    wx.request({
      url: app.globalData.requestUri + '/personal?actionName=approval&postId=' + postId + '&approvalStatus=' + approvalStatus,
      success: function (res) {
        wx.showToast({
          title: '审批成功',
          icon: 'none',
          duration: 2000,
          mask: true
        }),
          that.onLoad();
      }
    })
  }

  
})