public class EggDrop {

    public static void v0(int topFloor, Toss toss) {
        int f = 1;
        while (true) {
            if (toss.toss(f)) {
                break;
            }
            f++;
        }
    }

    public static void v1(int topFloor, Toss toss) {
        // binary search
        int l = 1;
        int h = topFloor;
        while (l <= h) {
            int mid = l + (h - l) / 2;
            if (toss.toss(mid)) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }
    }

    public static void v2(int topFloor, Toss toss) {
        // toss at 1, 2, 4, 8, ..., 2^k
        // if broken, binary search between 2^(k-1) ~ 2^k
        int end = 1;
        while (end <= topFloor) {
            if (toss.toss(end)) {
                break;
            }
            end *= 2;
        }
        end = end > topFloor ? topFloor : end;
        if (end > 1) {
            int start = end / 2;
            while (start <= end) {
                int mid = start + (end - start) / 2;
                if (toss.toss(mid)) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
    }

    public static void v3(int topFloor, Toss toss) {
        int step = (int) Math.sqrt(topFloor);
        int end = 1;
        while (end <= topFloor) {
            if (toss.toss(end)) {
                break;
            }
            end += step;
        }
        // ≈ (topFloor)^(1/2)
        end = end > topFloor ? topFloor : end;
        if (end > 1) {
            int i = end > step ? (end - step) : 1;
            while (i <= end) {
                if (toss.toss(i)) {
                    break;
                }
                i++;
            }
        }
    }

    public static void v4(int topFloor, Toss toss) {
        int k = 1;
        int end;
        // 1, 4, 9, 16, 25, ..., (k-1)^2
        while ((end = k * k) <= topFloor) {
            if (toss.toss(end)) {
                break;
            }
            k++;
        }
        // = k ≈ (target)^(1/2)
        end = end > topFloor ? topFloor : end;
        if (end > 1) {
            int i = (k - 1) * (k - 1);
            while (i <= end) {
                if (toss.toss(i)) {
                    break;
                }
                i++;
            }
            // ≈ (k)^2 - (k-1)^2 ≈ 2k ≈ 2(target)^(1/2)
        }
    }

    public static void main(String[] args) {
        int n = 100;
        TossTest tossTest = new TossTest(n, (int) (1 + Math.random() * (n + 1)));
        System.out.println("Top floor: " + tossTest.getTopFloor() + ", target: " + tossTest.getTarget());

        // eggs: 1, tosses: <= target
        // v0(n, tossTest);

        // eggs: lg(n), tosses: lg(n)
        // v1(n, tossTest);

        // eggs: lg(target), tosses: 2lg(target)
        // lg(100) ≈ 6.7
        // v2(n, tossTest);

        // eggs: 2, tosses: 2n^(1/2)
        // v3(n, tossTest);

        // eggs: 2, tosses: C(target)^(1/2)
        System.out.println("(target)^(1/2)=" + Math.sqrt(tossTest.getTarget()));
        v4(n, tossTest);

        if (!tossTest.isReachedTarget()) {
            throw new RuntimeException("test failed.");
        }
        System.out.println("Egges: " + tossTest.getEggs() + ", Tosses: " + tossTest.getTosses());

    }

    static class TossTest implements Toss {

        private final int topFloor;
        private final int target;
        private int eggs;
        private int tosses;
        private boolean reachedTarget;

        public TossTest(int topFloor, int target) {
            this.topFloor = topFloor;
            this.target = target;
            this.eggs = 0;
            this.tosses = 0;
            this.reachedTarget = false;
        }

        public int getEggs() {
            return eggs;
        }

        public int getTosses() {
            return tosses;
        }

        public int getTopFloor() {
            return topFloor;
        }

        public int getTarget() {
            return target;
        }

        public boolean isReachedTarget() {
            return reachedTarget;
        }

        @Override
        public boolean toss(int floor) {
            if (floor <= 0 || floor > topFloor) {
                throw new RuntimeException("Invalid floor number: " + floor);
            }
            tosses++;
            if (floor >= target) {
                eggs++;
            }
            if (floor == target) {
                reachedTarget = true;
            }
            return floor >= target;
        }

    }

    interface Toss {

        /**
         * toss an egg from specified floor
         * 
         * @param floor the floor
         * @return the egg is broken?
         */

        boolean toss(int floor);
    }
}