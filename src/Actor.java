import org.jsfml.graphics.Drawable;
import org.jsfml.graphics.RenderWindow;
import org.jsfml.graphics.Transformable;

public abstract class Actor {
    Transformable obj;   // Base object

    int x  = 0;	// Current X-coordinate
    int y  = 0;	// Current Y-coordinate

    int r  = 0;	// Change in rotation per cycle
    int dx = 5;	// Change in X-coordinate per cycle
    int dy = 5;	// Change in Y-coordinate per cycle

    //
    // Is point x, y within area occupied by this object?
    //
    // This should really be done with bounding boxes not points
    // ...we should check whether any point in bounding box
    //    is covered by this object
    //
   public boolean within (int x, int y) {
        // Should check object bounds here
        // -- we'd normally assume a simple rectangle
        //    ...and override as necessary
        return false;
    }

    public void clicked(){
       //this is going to be overridden by most actors
    }

    //
    // Work out where object should be for next frame
    //
   public void calcMove(int minx, int miny, int maxx, int maxy) {
        //
        // Add deltas to x and y position
        //
        x += dx;
        y += dy;

        //
        // Check we've not hit screen bounds
        // x and y would normally be the object's centre point
        // rather than its top corner so we can more easily
        // rotate objects and handle circular shapes
        //
        if (x <= minx || x >= maxx) { dx *= -1; x += dx; }
        if (y <= miny || y >= maxy) { dy *= -1; y += dy; }
    }

    //
    // Rotate and reposition the object
    //
    public void performMove( ) {
        obj.rotate(r);
        obj.setPosition(x,y);
    }

    //
    // Render the object at its new position
    //
    void draw(RenderWindow w) {
        w.draw((Drawable)obj);
    }
}