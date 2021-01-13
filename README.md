# BaseMvp
MVP Architecture Framework

MVP 架构的框架封装.能够完整地感受生命周期,可以自动释放View的视图绑定,防止内存泄漏。

[Android Studio插件辅助创建MVP代码](https://github.com/fengxiaocan/BaseMvpHelper/blob/master/BaseMvpHelper.jar)。用方法为:鼠标右键包路径处,选择New Kotlin Mvp或New Java Mvp,输入名称即可创建。

需要在Base Activity跟Base Fragment中实现MvpProvider.BaseMvpOwner,提供MvpStore。
然后使用MvpProvider获取Presenter的实例来操作方法，内部自动处理生命周期。
    
调用说明

    MvpProvider.of(owner/**MvpOwner拥有者*/, factory/**Class类创建工厂*/, lifecycle/**生命周期感应*/).get(XXXPresenter.class).xxxFun();

基类实现：

    public class BaseMvpActivity extends AppCompatActivity implements MvpProvider.BaseMvpOwner {
        private MvpStore mvpStore;
        private MvpProvider provider;
    
        @Override
        public MvpStore getMvpStore() {
            return mvpStore == null ? mvpStore = new MvpStore() : mvpStore;
        }
        
        public MvpProvider provider() {
            return provider == null ? provider = MvpProvider.of(this) : provider;
        }
    }

使用用例步骤:

1.创建Mvp Contract，定义Presenter、Model、View

    public interface LoginMvp {
        interface IPresenter extends IBasePresenter<LoginView> {
            void login(String userName, String password);
        }

        interface IModel extends IBaseModel {
            void login(String userName, String password, Observer<String> observer);
        }

        interface IView {
            void waiting();
            void loginSuccess(String message);
            void loginFailure(String message);
        }
    }
    
2.实现Model类

    public class LoginModel extends BaseModel implements LoginMvp.IModel {
        @Override
        public void login(String userName, String password, Observer<String> observer) {
            //...
        }
    }

3.实现Presenter类

    public class LoginPresenter extends BasePresenter<LoginModel, LoginMvp.IView> implements LoginMvp.IPresenter {
        @Override
        public void login(String userName, String password) {
            if (isActive()) {
                if (isViewAttached()) {
                    getView().waiting();
                }
                getModel().login(userName, password, new Observer<String>() {
                    ...
                });
            }
        }
    }

4.绑定IView视图到Activity或Fragment上

    public class MainActivity extends BaseMvpActivity implements LoginMvp.IView {
        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            //获取实例绑定IView视图
            provider().get(LoginPresenter.class).attachView(this);
        }

        public void viewClick(View view) {
            //获取实例
            provider().get(LoginPresenter.class).login(getUserName(), getPassword());
        }

        @Override
        public void waiting() {
            ...
        }
    
        @Override
        public void loginSuccess(String message) {
            ...
        }
    
        @Override
        public void loginFailure(String message) {
            ...
        }
    }
        
