package beans;

/**
 * 作者：zzx on 2020/10/13 20:54
 * <p>
 * 作用： xxxx
 */
public class OtherBeans {
    private String title;
    private String smallTitle;
    private int currentProgress;
    private int finalyProgress;
    private int imag;


    public OtherBeans(Builder builder) {
        this.title=builder.title;
        this.smallTitle=builder.smallTitle;
        this.currentProgress=builder.currentProgress;
        this.finalyProgress=builder.finalyProgress;
        this.imag=builder.imag;
    }


    public static class Builder{
        private String title;
        private String smallTitle;
        private int currentProgress;
        private int finalyProgress;
        private int imag;

        public Builder(String title) {
            this.title = title;
        }


        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setSmallTitle(String smallTitle) {
            this.smallTitle = smallTitle;
            return this;
        }

        public Builder setCurrentProgress(int currentProgress) {
            this.currentProgress = currentProgress;
            return this;
        }

        public Builder setFinalyProgress(int finalyProgress) {
            this.finalyProgress = finalyProgress;
            return this;
        }

        public Builder setImag(int imag) {
            this.imag = imag;
            return this;
        }

        public OtherBeans build(){
            return new OtherBeans(this);
        }
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSmallTitle() {
        return smallTitle;
    }

    public void setSmallTitle(String smallTitle) {
        this.smallTitle = smallTitle;
    }

    public int getCurrentProgress() {
        return currentProgress;
    }

    public void setCurrentProgress(int currentProgress) {
        this.currentProgress = currentProgress;
    }

    public int getFinalyProgress() {
        return finalyProgress;
    }

    public void setFinalyProgress(int finalyProgress) {
        this.finalyProgress = finalyProgress;
    }

    public int getImag() {
        return imag;
    }

    public void setImag(int imag) {
        this.imag = imag;
    }
}
